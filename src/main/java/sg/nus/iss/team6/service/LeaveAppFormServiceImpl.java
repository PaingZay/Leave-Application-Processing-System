package sg.nus.iss.team6.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.model.ApplicationStatus;

@Service
public class LeaveAppFormServiceImpl implements LeaveAppFormService {
//	  @Resource
//	  private LeaveAppRepository laRepository;

//	@Override
//	public LeaveApplication convertToLA(
//			LeaveAppForm leaveAppForm,
//			LocalDateTime applicationDate,
//			LeaveType leavetype,
//			Employee desiredEmployee,
//			LocalDateTime newEnd) {
//		
//		long secondsBetween= (long) Duration.between(
//				leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(),0,0), 
//				leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(),0,0))
//				.toSeconds();
//		LeaveApplication myLA = new LeaveApplication();
//		
//		myLA.setApplicationDate(applicationDate);
//		myLA.setApprovalDate(null);
//		myLA.setLeaveStartDate(leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(),0,0));
//		
//		if(newEnd==null) {
//			myLA.setLeaveEndDate(leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(),0,0));
//		}
//		else {
//			myLA.setLeaveEndDate(newEnd);
//		}
//		
//		myLA.setLeaveDuration(secondsBetween);
//		
//		myLA.setWorkDelegate(desiredEmployee);
//		myLA.setOverseasPhone(leaveAppForm.getOverseasPhone());
//		myLA.setStatus(ApplicationStatus.APPLIED);
//		myLA.setComment(null);
//		myLA.setReason(leaveAppForm.getReason());
//		myLA.setActive(true);
//		myLA.setLeaveType(leavetype);
//		
//		return myLA;
//	}

}
