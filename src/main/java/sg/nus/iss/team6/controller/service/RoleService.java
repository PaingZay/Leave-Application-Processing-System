package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveType;

@Service
public interface RoleService {

	List<LeaveType> findLeaveTypesByRole(int rid);
}
