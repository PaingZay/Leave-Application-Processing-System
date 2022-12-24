package sg.nus.iss.team6.service;

import java.util.List;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;

public interface EmployeeService {
	public Employee findByName(String username, String password);

	public Employee findUser(String username);

	List<Employee> findAllEmployees();
	
	List<Employee> findAllTeamEmployees(Integer tid);

	List<String> findAllUsernames();
	
	Employee createEmployee(Employee employee);

	Employee findEmployee(Integer eid);

	Employee findEmployeeByName(String name);

//	List<Employee> findEmployeesByTeam(Integer tid);

	Integer findEmployeeRoleId(Integer eid);

	Employee changeEmployee(Employee employee);
	
	Integer findTIDByEmployee(Integer empId);


	public List<LeaveApplication> getLeaveApplicationsForPeriodAndType(Employee employee, Integer yearNum, LeaveType leaveType);
	
	public void addLeaveApplication(Employee employee, LeaveApplication la);
	
	Employee findEmployeeByUserName(String name);


	Employee findManagerByTeamId(Integer teamId);
}
