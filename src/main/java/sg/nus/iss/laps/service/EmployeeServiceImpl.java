package sg.nus.iss.laps.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.laps.model.Employee;
import sg.nus.iss.laps.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Resource
	  private EmployeeRepository employeeRepository;
	
	@Transactional
	  @Override
	  public Employee authenticate(String username, String pwd) {
		return employeeRepository.findUserByNamePwd(username, pwd);
	  }
}
