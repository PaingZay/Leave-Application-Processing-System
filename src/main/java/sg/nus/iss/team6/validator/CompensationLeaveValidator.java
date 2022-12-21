package sg.nus.iss.team6.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team6.controller.service.EmployeeService;
import sg.nus.iss.team6.controller.service.LeaveTypeService;
import sg.nus.iss.team6.controller.service.OvertimeChitService;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.OvertimeChit;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.util.ApplicationStatus;
import sg.nus.iss.team6.util.ldt;

@Component
public class CompensationLeaveValidator implements Validator {

	@Autowired
	private EmployeeService eService;

	@Autowired
	private OvertimeChitService otcService;

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

		if (leaveAppForm.getLeaveStartDate() != null && leaveAppForm.getLeaveEndDate() != null
				&& leaveAppForm.getApplicantId() != null) {

			// validate annual leave limit
			Employee currentUser = eService.findEmployee(leaveAppForm.getApplicantId());
			LeaveType leaveType = ltService.findLeaveTypeByNameAndRole("Compensation", currentUser.getRole());

			// query from overtimeChit
			long maxEntitlementInSeconds = 0;
			// initialize holder variable
			long appliedLeavesInSeconds = 0;
			
			List<OvertimeChit> employeeChits = otcService.findOvertimechitsByEmployee(leaveAppForm.getApplicantId());

			//Get Total Duration for Chits-------------------------------------------------
			if (!employeeChits.isEmpty() && employeeChits != null) {

				List<OvertimeChit> chitsToRemove = new ArrayList<>();
				LocalDateTime yearStart =LocalDateTime.of(leaveAppForm.getLeaveStartDate().getYear(),1,1, 0,0,0);
				LocalDateTime yearEnd =LocalDateTime.of(leaveAppForm.getLeaveStartDate().getYear(),12,31, 23,59,59);
				
				for (OvertimeChit otcChit : employeeChits) {
					if (otcChit.getStatus() != ApplicationStatus.APPROVED) {
						chitsToRemove.add(otcChit);
					}
					else if (!ldt.isOverlap(yearStart, yearEnd, otcChit.getOtStart(), otcChit.getOtEnd())){
						//remove chits not from same year
						chitsToRemove.add(otcChit);
					}
				}
				// remove non-approved chits
				employeeChits.removeAll(chitsToRemove);
			}
			// loop through list to add duration
			for (OvertimeChit otcChit : employeeChits) {
				long temp = ChronoUnit.SECONDS.between(otcChit.getOtStart(), otcChit.getOtEnd());
				maxEntitlementInSeconds += temp;
			}
			
			//Get AppliedLeaves Balance-------------------------------------------------
			if (currentUser.getLeaveApplications() != null) {

				// get all leaves for the specific year
				Integer yearToValidate = leaveAppForm.getLeaveStartDate().getYear();
				List<LeaveApplication> appliedLeaves = currentUser.getLeaveApplicationsForPeriodAndType(yearToValidate,
						leaveType);
				List<LeaveApplication> leavesToRemove = new ArrayList<>();
				// remove deleted
				// then remove IF its cancelled OR rejected
				for (LeaveApplication la : appliedLeaves) {
					if (la.getActive() == false) {
						leavesToRemove.add(la);
					} else if (la.getStatus() == ApplicationStatus.CANCELLED
							|| la.getStatus() == ApplicationStatus.REJECTED) {
						leavesToRemove.add(la);
					}
				}
				appliedLeaves.removeAll(leavesToRemove);

				// compute amount to subtract from balance
				for (LeaveApplication la : appliedLeaves) {
					long temp = la.getLeaveDuration();
					appliedLeavesInSeconds += temp;
				}
			}
			
			//Get IntendedLeavesInSeconds-------------------------------------------------
			long intendedLeavesInSeconds = ChronoUnit.SECONDS.between(
					leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0),
					leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0));
			
			//Conditional-------------------------------------------------------------------
			if (maxEntitlementInSeconds < appliedLeavesInSeconds + intendedLeavesInSeconds) {
				errors.reject("leaveEndDate",
						"You don't have sufficient remaining leave balance for that leave duration!");
				errors.rejectValue("leaveEndDate", "error.dates",
						"You don't have sufficient remaining leave balance for that leave duration!");
			}

		}
	}
}
