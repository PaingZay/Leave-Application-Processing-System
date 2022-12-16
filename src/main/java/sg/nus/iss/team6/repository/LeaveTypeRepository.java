package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.team6.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{

	  @Query("SELECT lt from LeaveType lt WHERE lt.Id = :ltid")
	  List<LeaveType> findLeaveTypeByLTID(@Param("ltid") Integer ltid);
	  
	  @Query("SELECT lt from LeaveType lt WHERE lt.Id = :ltid AND lt.active =true ")
	  List<LeaveType> findActiveLeaveTypeByLTID(@Param("ltid") Integer ltid);
	  
	  @Query(value = "SELECT * FROM course WHERE status = ?0", nativeQuery = true)
	  List<LeaveType> findPendingCoursesByStatus(String status);
}
