package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer>{

		//Can't figure this out	
	//	  @Query("SELECT la from Employee e JOIN e.leaveApplications la WHERE e.id = :eid")
//	  List<LeaveApplication> findLeaveApplicationByEID(@Param("eid") int eid);
	  
	  //backup
	  ////@Query("SELECT la from LeaveApplication la WHERE la.employeeId = :eid")
}
