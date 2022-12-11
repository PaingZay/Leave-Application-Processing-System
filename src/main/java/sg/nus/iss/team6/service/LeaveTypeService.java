package sg.nus.iss.team6.service;

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
	public List<LeaveType> findAll(){
		List<LeaveType> listLeaveType=leaveTypeRepo.findAll();
		
		return listLeaveType;
	}
	
	public LeaveType save(LeaveType lt) {
		LeaveType leaveType=leaveTypeRepo.save(lt);
		
		return leaveType;
	}
	
	public void delete(LeaveType lt) {
		
		leaveTypeRepo.delete(lt);
	}
}
