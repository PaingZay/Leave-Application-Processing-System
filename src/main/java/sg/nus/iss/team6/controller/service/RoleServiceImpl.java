package sg.nus.iss.team6.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	  @Resource
	  private RoleRepository rRepository;
	  
	  @Override
	  public List<LeaveType> findLeaveTypesByRole(int rid){
		  return rRepository.findLeaveTypesByRole(rid);
	  }


	
}
