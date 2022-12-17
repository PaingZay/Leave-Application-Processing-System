package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;

@Service
public interface EmployeeService {

	  List<Employee> findAllEmployees();

	  Employee findEmployee(Integer eid);
	  
	  Employee findEmployeeByName(String name);
	  
	  List<Employee> findEmployeesByTeam(Integer tid);
	  
	  Integer findEmployeeRoleId (Integer eid);
	  

	  
	  

}
