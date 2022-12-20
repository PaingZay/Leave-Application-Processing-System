package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.OvertimeChit;

@Service
public interface OvertimeChitService {

	List<OvertimeChit> findAllOvertimechits();
	
	List<OvertimeChit> findOvertimechitsByEmployee(Integer eid);
	
	OvertimeChit findOvertimeChit(Integer otcId);

}
