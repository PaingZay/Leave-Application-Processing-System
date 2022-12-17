package sg.nus.iss.team6.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.EmployeeRepository;

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
