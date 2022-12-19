package sg.nus.iss.team6.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer>{

	@Query("SELECT la "
			+ "from LeaveApplication la"
			+ " WHERE la.active = :active")
	List<LeaveApplication> findLeaveApplicationByActivity(@Param("active") int active);
	


		//Can't figure this out	
	//	  @Query("SELECT la from Employee e JOIN e.leaveApplications la WHERE e.id = :eid")
//	  List<LeaveApplication> findLeaveApplicationByEID(@Param("eid") int eid);
	  
	  //backup
	  ////@Query("SELECT la from LeaveApplication la WHERE la.employeeId = :eid")
	
	
}
