package sg.nus.iss.team6.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	  @Resource
	  private EmployeeRepository eRepository;
	  
	  @Override
	  public 
	  List<Employee> findAllEmployees(){
		  return eRepository.findAll();
	  }

	  @Override
	  @Transactional
	  public Employee findEmployee(Integer eid) {
		  return eRepository.findById(eid).orElse(null);
	  }
	  
	  @Override
	  @Transactional
	  public List<Employee> findEmployeesByTeam(Integer tid) {
		  return eRepository.findEmployeeByTID(tid);
	  }
}
