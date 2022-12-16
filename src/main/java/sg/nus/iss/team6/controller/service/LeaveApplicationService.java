package sg.nus.iss.team6.controller.service;

import java.util.List;
import sg.nus.iss.team6.model.LeaveApplication;

public interface LeaveApplicationService {

	  List<LeaveApplication> findAllLeaveApplications();

	  LeaveApplication findLeaveApplication(Integer laid);

	  LeaveApplication createLeaveApplication(LeaveApplication leaveApplication);

	  LeaveApplication changeLeaveApplication(LeaveApplication LeaveApplication);

	  void removeLeaveApplication(LeaveApplication LeaveApplication);

//	  List<LeaveApplication> findLeaveApplicationByEID(int eid);

	}