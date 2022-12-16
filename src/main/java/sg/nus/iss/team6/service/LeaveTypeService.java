package sg.nus.iss.team6.service;

import java.util.List;

import sg.nus.iss.team6.model.LeaveType;


public interface LeaveTypeService {
	List<LeaveType> findAllLeaveType();
	LeaveType findLeaveType(Integer leaveTypeId);
	LeaveType createLeaveType(LeaveType leaveType);
	LeaveType changeLeaveType(LeaveType leaveType);
	void removeLeaveType(LeaveType leaveType);
	LeaveType findLeaveTypesByName(String name);
}
