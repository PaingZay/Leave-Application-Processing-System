package sg.nus.iss.team6.service;

import java.time.LocalDateTime;
import java.util.List;

import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.LeaveApplication;


public interface LeaveApplicationService
{
	List<LeaveApplication> findAllApplication();
//	
	public void addBooking(LeaveApplication application);
	
	LeaveApplication findApplication(Integer id);
	
	LeaveApplication changeApplication(LeaveApplication application);

	void updateApplicationById(Integer id,String cmt, ApplicationStatus status, LocalDateTime approvalDate);
	
	List<LeaveApplication> findAllLeaveApplications();

	LeaveApplication findLeaveApplication(Integer laid);

	LeaveApplication createLeaveApplication(LeaveApplication leaveApplication);

	LeaveApplication changeLeaveApplication(LeaveApplication LeaveApplication);

	void removeLeaveApplication(LeaveApplication LeaveApplication);

	List<LeaveApplication> findApplicationsByTeamId(Integer teamId);

	List<LeaveApplication> findLeaveHistoryByTeamId(Integer teamId);
}
