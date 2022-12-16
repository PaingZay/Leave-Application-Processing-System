package sg.nus.iss.team6.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	public LeaveTypeServiceImpl() {
		// TODO Auto-generated constructor stub 
	}

	@Autowired
	private LeaveTypeRepository leaveTypeRepo;
	
	//maintain the leave types
	//Retrieve all leave Type
	@Transactional
	@Override
	public List<LeaveType> findAllLeaveType(){
		return leaveTypeRepo.findAll();
	}
	
	@Transactional
	@Override
	public LeaveType findLeaveType(Integer Id) {
		return leaveTypeRepo.findById(Id).orElse(null);
	}
	
	// Create and insert new Leave type
	@Transactional
	@Override
	public LeaveType createLeaveType(LeaveType leaveType) {
		return leaveTypeRepo.saveAndFlush(leaveType);
	}
	
	@Transactional
	@Override
	public LeaveType changeLeaveType(LeaveType leaveType) {
		return leaveTypeRepo.saveAndFlush(leaveType);
	}
	
	// Remove exist Leave Type
	@Transactional
	@Override
	public void removeLeaveType(LeaveType leaveType) {
		leaveTypeRepo.delete(leaveType);
	}

	@Transactional
	@Override
	public LeaveType findLeaveTypesByName(String name) {
		return leaveTypeRepo.findLeaveTypeByName(name);
	}
}
