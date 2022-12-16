package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.team6.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{

	/*
	 * @Query("SELECT lt from LeaveType lt WHERE lt.Id = :Id") List<LeaveType>
	 * findLeaveTypeById(@Param("Id") Integer Id);
	 * 
	 * @Query("SELECT lt from LeaveType lt WHERE lt.Id = :Id AND lt.active =true ")
	 * List<LeaveType> findActiveLeaveTypeById(@Param("Id") Integer Id);
	 */
	  
	  @Query("SELECT lt from LeaveType lt WHERE lt.name = :name")
	  LeaveType findLeaveTypeByName(@Param("name") String name);

	
}
