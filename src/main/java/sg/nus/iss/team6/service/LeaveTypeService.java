package sg.nus.iss.team6.service;

import java.util.List;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;


public interface LeaveTypeService {
	List<LeaveType> findAllLeaveType();
	LeaveType findLeaveType(Integer leaveTypeId);
	LeaveType createLeaveType(LeaveType leaveType);
	LeaveType changeLeaveType(LeaveType leaveType);
	void removeLeaveType(LeaveType leaveType);
	LeaveType findLeaveTypesByName(String name);
	LeaveType findLeaveTypeByNameAndRole(String name,Role role);
	List<LeaveType> findAll();
	LeaveType save(LeaveType lt);
	void delete(LeaveType lt);
}
