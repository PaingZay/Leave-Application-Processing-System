package sg.nus.iss.team6.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.LeaveApplication;

@Service
public interface LeaveApplicationService
{
	List<LeaveApplication> findAllApplication();
//	
	public void addBooking(LeaveApplication application);
	
	LeaveApplication findApplication(Integer id);
	
	LeaveApplication changeApplication(LeaveApplication application);

}
