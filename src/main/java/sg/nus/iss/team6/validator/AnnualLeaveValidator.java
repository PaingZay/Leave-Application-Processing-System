package sg.nus.iss.team6.validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.service.EmployeeService;
import sg.nus.iss.team6.service.LeaveTypeService;
import sg.nus.iss.team6.util.ldt;

@Component
public class AnnualLeaveValidator implements Validator {

	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private LeaveTypeService ltService;

	@Override
	public boolean supports(Class<?> clazz) {		
		return LeaveAppForm.class.isAssignableFrom(clazz);

	}


	@Override
	public void validate(Object target, Errors errors) {

		LeaveAppForm leaveAppForm = (LeaveAppForm) target;
		LocalDateTime now = LocalDateTime.now();
		
		//------add------------
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		String username = (String) session.getAttribute("username");
		Employee currentUser = eService.findEmployeeByUserName(username);
		int currentUserId = eService.findEmployeeByUserName(username).getId();
		//---------------------
		
		if (leaveAppForm.getLeaveStartDate() != null && leaveAppForm.getLeaveEndDate() != null && currentUser!=null) {

			//validate annual leave limit
			LeaveType leaveType = ltService.findLeaveTypeByNameAndRole("Annual",currentUser.getRole());
			//14-18 days *24hrs*60min*60sec
			Long maxEntitlementInSeconds = (long) (leaveType.getMaxEntitlement()*24*60*60);
			
			if(currentUser.getLeaveApplications() != null) {
				
				//initialize holder variable
				long appliedLeavesInSeconds=0;
				//get all leaves for the specific year
				Integer yearToValidate = leaveAppForm.getLeaveStartDate().getYear();
				List<LeaveApplication> appliedLeaves = eService.getLeaveApplicationsForPeriodAndType(currentUser,yearToValidate,leaveType);
				List<LeaveApplication> leavesToRemove = new ArrayList<>();
				//remove deleted
				//then remove IF its cancelled OR rejected
				for (LeaveApplication la:appliedLeaves) {
					if (la.getActive()==false) {
						leavesToRemove.add(la);
					}
					else if (la.getStatus()==ApplicationStatus.CANCELLED ||
							la.getStatus()==ApplicationStatus.REJECTED) {
						leavesToRemove.add(la);
					}
				}
				appliedLeaves.removeAll(leavesToRemove);
								
				//compute amount to subtract from balance
				for (LeaveApplication la:appliedLeaves) {
					long temp = la.getLeaveDuration();
					appliedLeavesInSeconds+=temp;
				}
				//set to LDT for both
				long intendedLeavesInSeconds = ChronoUnit.SECONDS.between(
						leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(),0,0),
						leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(),0,0));
				
				if(maxEntitlementInSeconds < appliedLeavesInSeconds+intendedLeavesInSeconds) {
					errors.reject("leaveEndDate", "You don't have sufficient remaining leave balance for that leave duration!");
					errors.rejectValue("leaveEndDate", "error.dates", "You don't have sufficient remaining leave balance for that leave duration!");
				}
				
				System.out.print("AnnualLeaveValidator exited successfully.");

			}
		}
	}
}
