package sg.nus.iss.laps.service;

import java.util.List;

import sg.nus.iss.laps.model.Employee;

public interface UserService {
	public Employee findByName(String username, String password);
	public Employee findUser(String username);
	List<Employee> findAllEmployees();

}
