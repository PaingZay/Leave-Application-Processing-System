package sg.nus.iss.laps.service;

import sg.nus.iss.laps.model.Employee;

public interface EmployeeService {
	Employee authenticate(String username, String pwd);
}
