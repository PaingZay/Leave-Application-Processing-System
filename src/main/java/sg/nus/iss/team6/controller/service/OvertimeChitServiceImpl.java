package sg.nus.iss.team6.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.OvertimeChit;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.repository.OvertimeChitRepository;

@Service
public class OvertimeChitServiceImpl implements OvertimeChitService{
	  @Resource
	  private OvertimeChitRepository otcRepository;

	@Override
	public List<OvertimeChit> findAllOvertimechits() {
		return otcRepository.findAll();
	}

	@Override
	public List<OvertimeChit> findOvertimechitsByEmployee(Integer eid) {
		return otcRepository.findOvertimechitsByEmployee(eid);
	}

	@Override
	public OvertimeChit findOvertimeChit(Integer otcId) {
		return otcRepository.findById(otcId).orElse(null);
	}
	  
	
}
