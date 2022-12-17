package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveType;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{

	//to implement- get days?
}
