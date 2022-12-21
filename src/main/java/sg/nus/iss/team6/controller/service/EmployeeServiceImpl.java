package sg.nus.iss.team6.controller.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.util.ldt;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Resource
	private EmployeeRepository eRepository;

	@Override
	public List<Employee> findAllEmployees() {
		return eRepository.findAll();
	}

	@Override
	@Transactional
	public Employee findEmployee(Integer eid) {
		return eRepository.findById(eid).orElse(null);
	}

	@Override
	@Transactional
	public Employee findEmployeeByName(String name) {
		return eRepository.findEmployeeByName(name);
	}

	@Override
	@Transactional
	public List<Employee> findEmployeesByTeam(Integer tid) {
		return eRepository.findEmployeeByTID(tid);
	}

	@Override
	@Transactional
	public Integer findEmployeeRoleId(Integer eid) {
		return eRepository.findEmployeeEmployeeRoleId(eid);
	}

	@Override
	@Transactional
	public Employee changeEmployee(Employee employee) {
		return eRepository.saveAndFlush(employee);
	}

	@Override
	@Transactional
	public Integer findTIDByEmployee(Integer empId) {
		return eRepository.findTIDByEmployee(empId);
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

}
