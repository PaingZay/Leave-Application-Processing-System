package sg.nus.iss.team6.service;

import java.util.List;

import sg.nus.iss.team6.model.Employee;

public interface EmployeeService {
	public Employee findByName(String username, String password);
	public Employee findUser(String username);
	List<Employee> findAllEmployees();
	List<String> findAllUsernames();
	Employee createEmployee(Employee employee);
}
