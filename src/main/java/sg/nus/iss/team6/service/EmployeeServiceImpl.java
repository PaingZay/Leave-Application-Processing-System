package sg.nus.iss.team6.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.util.ldt;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Resource
	  private EmployeeRepository employeeRepository;
	  
	  @Override
	  @Transactional
	  public Employee findByName(String username, String password) {
	    return employeeRepository.findByName(username, password);
	  }
	  
	  @Override
	  @Transactional
	  public Employee findUser(String username) {
	    return employeeRepository.findUser(username);
	  }
	  
	  @Override
	  @Transactional
	  public List<Employee> findAllEmployees() {
	    return employeeRepository.findAll();
	  }
	  
	  @Override
	  @Transactional
	  public List<Employee> findAllTeamEmployees(Integer tid){
		  return employeeRepository.findAllTeamEmployees(tid);
	  }
	  
	  @Override
	  @Transactional
	  public List<String> findAllUsernames() {
	    return employeeRepository.findAllUsernames();
	  }
	  
	  @Override
	  @Transactional
	  public Employee createEmployee(Employee employee) {
	    return employeeRepository.saveAndFlush(employee);
	  }

	  @Override
	  @Transactional
	  public Employee findEmployee(Integer eid) {
		  return employeeRepository.findById(eid).orElse(null);
	  }
	  
	  @Override
	  @Transactional
	  public Employee findEmployeeByName(String name) {
		  return employeeRepository.findEmployeeByName(name);
	  }
	  
//	  @Override
//	  @Transactional
//	  public List<Employee> findEmployeesByTeam(Integer tid) {
//		  return employeeRepository.findEmployeeByTID(tid);
//	  }
	  
	  

	  @Override
	  @Transactional
	  public Integer findEmployeeRoleId (Integer eid) {
		  return employeeRepository.findEmployeeEmployeeRoleId(eid);
	  }
	  
	  @Override
	  @Transactional
	  public Employee changeEmployee(Employee employee) {
	    return employeeRepository.saveAndFlush(employee);
	  }
	  
	  @Override
	  @Transactional
	  public Integer findTIDByEmployee(Integer empId) {
		  return employeeRepository.findTIDByEmployee(empId);
	  }


	@Override
	@Transactional
	public List<LeaveApplication> getLeaveApplicationsForPeriodAndType(Employee employee, Integer yearNum, LeaveType leaveType) {

		List<LeaveApplication> toReturn = employee.getLeaveApplications();
		List<LeaveApplication> leavesToRemove = new ArrayList<>();

		LocalDateTime yearStart = LocalDateTime.of(yearNum, 1, 1, 0, 0, 0);
		LocalDateTime yearEnd = LocalDateTime.of(yearNum, 12, 31, 23, 59, 59);

		for (LeaveApplication la : toReturn) {
			if (la.getLeaveType() != leaveType) {
				leavesToRemove.add(la);
			} else if (!ldt.isOverlap(yearStart, yearEnd, la.getLeaveStartDate(), la.getLeaveEndDate())) {
				leavesToRemove.add(la);
			}
		}
		toReturn.removeAll(leavesToRemove);
		return toReturn;
	}

	@Override
	@Transactional
	public void addLeaveApplication(Employee employee, LeaveApplication la) {
		if (employee.getLeaveApplications() == null) {
			List<LeaveApplication> toAdd = new ArrayList<>();
			toAdd.add(la);
			employee.setLeaveApplications(toAdd);
		} else {
			List<LeaveApplication> toAdd =employee.getLeaveApplications();
			toAdd.add(la);
			employee.setLeaveApplications(toAdd);
		}
	}
	
	  @Override
	  @Transactional
	  public Employee findEmployeeByUserName(String name) {
		  return employeeRepository.findEmployeeByUserName(name);
	  }

	@Override
	@Transactional
	public Employee findManagerByTeamId(Integer teamId)
	{
		var allTeamEmployeeLst =  findAllTeamEmployees(teamId);
		for(var e: allTeamEmployeeLst)
		{
			if(e.getRole().getName() == "manager")
			{
				return e;
			}
		}
		return null;
	}
	  
}
