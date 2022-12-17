package sg.nus.iss.team6.service;

import sg.nus.iss.team6.model.Employee;

public interface EmployeeService {
	Employee authenticate(String username, String pwd);
}
