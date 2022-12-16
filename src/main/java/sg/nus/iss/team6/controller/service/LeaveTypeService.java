package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	public LeaveTypeService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private LeaveTypeRepository leaveTypeRepo;
	
	//maintain the leave types
	//Retrieve all leave Type
	public List<LeaveType> findAll(){
		List<LeaveType> listLeaveType=leaveTypeRepo.findAll();
		
		return listLeaveType;
	}
	
	// Create and insert new Leave type
	public LeaveType save(LeaveType lt) {
		LeaveType leaveType=leaveTypeRepo.save(lt);
		
		return leaveType;
	}
	
	// Remove exist Leave Type
	public void delete(LeaveType lt) {
		
		leaveTypeRepo.delete(lt);
	}
}
