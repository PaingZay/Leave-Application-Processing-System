package sg.nus.iss.team6.service;

import java.util.List;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;


public interface RoleService {

	List<LeaveType> findLeaveTypesByRole(int rid);
	List<Role> findAllRoles();
	Role findRole(Integer roleId);
	Role createRole(Role role);
	Role changeRole(Role role);
	void removeRole(Role role);
	List<String> findAllRolesNames();
	List<Role> findRoleByName(String name);
}
