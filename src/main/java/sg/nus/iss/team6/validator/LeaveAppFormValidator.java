package sg.nus.iss.team6.validator;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team6.controller.service.EmployeeService;
import sg.nus.iss.team6.controller.service.LeaveTypeService;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.util.ldt;

@Component
public class LeaveAppFormValidator implements Validator {

	@Autowired
	private EmployeeService eService;
	
	// TODO: refactor to autowired
	private LeaveTypeService ltService = new LeaveTypeService();

	@Override
	public boolean supports(Class<?> clazz) {		
		return LeaveAppForm.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {

		LeaveAppForm leaveAppForm = (LeaveAppForm) target;
		LocalDateTime now = LocalDateTime.now();
		
		if (leaveAppForm.getLeaveStartTime() != null && leaveAppForm.getLeaveEndTime() != null) {
			
			if (leaveAppForm.getLeaveStartTime() < 0|| leaveAppForm.getLeaveEndTime()< 0) {
				errors.reject("leaveStartTime", "Start/End time cannot be negative.");
				errors.rejectValue("leaveStartTime", "error.dates", "Start/End time cannot be negative.");
			}
			
			if (leaveAppForm.getLeaveStartTime()>23 || leaveAppForm.getLeaveEndTime() >23) {
				errors.reject("leaveStartTime", "If you wish to book leave extending past the end day, please select the following day.");
				errors.rejectValue("leaveStartTime", "error.dates", "If you wish to book leave extending past the end day, please select the following day.");
			}
			

		}
		

		if (leaveAppForm.getLeaveStartDate() != null && leaveAppForm.getLeaveEndDate() != null) {

			if (!ldt.isValid(leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0),
					leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0))) {
				errors.reject("leaveEndDate", "The end date comes before the start date.");
				errors.rejectValue("leaveEndDate", "error.dates", "The end date comes before the start date.");
			}

			if (!ldt.isValid(now, leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0))) {
				errors.reject("leaveStartDate", "You cannot apply for leave retroactively.");
				errors.rejectValue("leaveStartDate", "error.dates", "You cannot apply for leave retroactively.");
			}

			if (leaveAppForm.getLeaveStartDate().getYear() != leaveAppForm.getLeaveEndDate().getYear()) {
				errors.reject("leaveEndDate", "Leave must not cross between years.");
				errors.rejectValue("leaveEndDate", "error.dates", "Leave must not cross between years.");
				//do not allow to cross x days/year maxEntitlement
			}
			
		}

		

		if (leaveAppForm.getApplicantId() != null) {
			
			Employee currentUser=eService.findEmployee(leaveAppForm.getApplicantId());
			
			if(currentUser.getLeaveApplications() != null) {
				
				// get current user's appliedLeaves
				List<LeaveApplication> appliedLeaves = currentUser.getLeaveApplications();

				// debug purpose
					// for (LeaveType lt : availableLT)
					// System.out.println("Item number: "+lt.getDescription()+"
					// "+lt.getMaxEntitlement()+" "+lt.getMinGranularity());

				// find employees from the same team
				List<Employee> teamEmployees = eService.findEmployeesByTeam(eService.findTIDByEmployee(leaveAppForm.getApplicantId()));
				// remove self from the team list
				teamEmployees.remove(eService.findEmployee(leaveAppForm.getApplicantId()));
				// look for desired employee
				Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());
				
				if(desiredEmployee ==null && leaveAppForm.getWorkDelegate().trim().length() > 0) {
					errors.reject("workDelegate", "Colleague does not exist!");
					errors.rejectValue("workDelegate", "error.dates",
							"Colleague does not exist!");
				}

				//TODO: debug
				if (!teamEmployees.contains(desiredEmployee) && desiredEmployee !=null) {
					errors.reject("workDelegate", "Please delegate your tasks to a colleague on the same team.");
					errors.rejectValue("workDelegate", "error.dates",
							"Please delegate your tasks to a colleague on the same team.");
				}

				// check for overlaps with previous leave
				for (LeaveApplication prevLeave : appliedLeaves) {

					if (ldt.isOverlap(leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0),
							leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0), prevLeave.getLeaveStartDate(),
							prevLeave.getLeaveEndDate())) {
						errors.reject("leaveStartDate", "You have already applied for leave that overlaps this date.");
						errors.rejectValue("leaveStartDate", "error.dates",
								"You have already applied for leave that overlaps this date.");
					}
				}
			}
		}


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveStartDate", "error.leaveStartDate",
				"Start date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveEndDate", "error.leaveEndDate",
				"End date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveStartTime", "error.leaveStartTime",
				"Start time is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveEndTime", "error.leaveEndTime",
				"End time is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "error.reason", "Reason is required.");
	}

}
