package sg.nus.iss.team6.service;

import java.util.List;

import javax.annotation.Resource;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Resource
	  private EmployeeRepository employeesRepository;
	  
	  @Override
	  @Transactional
	  public Employee findByName(String username, String password) {
	    return employeesRepository.findByName(username, password);
	  }
	  
	  @Override
	  @Transactional
	  public Employee findUser(String username) {
	    return employeesRepository.findUser(username);
	  }
	  
	  @Override
	  @Transactional
	  public List<Employee> findAllEmployees() {
	    return employeesRepository.findAll();
	  }
	  
	  @Override
	  @Transactional
	  public List<String> findAllUsernames() {
	    return employeesRepository.findAllUsernames();
	  }
	  
	  @Override
	  @Transactional
	  public Employee createEmployee(Employee employee) {
	    return employeesRepository.saveAndFlush(employee);
	  }
}
