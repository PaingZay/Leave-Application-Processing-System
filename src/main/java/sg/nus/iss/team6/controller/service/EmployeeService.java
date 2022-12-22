package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;

@Service
public interface EmployeeService {

	List<Employee> findAllEmployees();

	Employee findEmployee(Integer eid);

	Employee findEmployeeByName(String name);

	List<Employee> findEmployeesByTeam(Integer tid);

	Integer findEmployeeRoleId(Integer eid);

	Employee changeEmployee(Employee employee);
	
	Integer findTIDByEmployee(Integer empId);
	
	public List<LeaveApplication> getLeaveApplicationsForPeriodAndType(Employee employee, Integer yearNum, LeaveType leaveType);
	
	public void addLeaveApplication(Employee employee, LeaveApplication la);
	
	Employee findEmployeeByUserName(String name);

}
