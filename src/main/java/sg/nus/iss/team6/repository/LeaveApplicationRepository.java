package sg.nus.iss.team6.repository;

import java.time.LocalDateTime;
//import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

	@Query("SELECT la "
	+ "from LeaveApplication la"
	+ " WHERE la.active = :active")
List<LeaveApplication> findLeaveApplicationByActivity(@Param("active") int active);
	
//	   @Query("Select la from leaveApplication la where :date between la.leaveStartDate AND la.leaveEndDate AND la.facility.id = :fid")
//	   public List<Application> findBooking(@Param("date") LocalDate date, @Param("fid") Integer fid);

@Modifying
@Query("update LeaveApplication la set la.comment = :cmt, la.status = :status, la.approvalDate = :approvalDate where la.id = :id")
	void updateApplicationById(@Param("id") Integer id, @Param("cmt") String cmt, @Param("status") ApplicationStatus status,@Param("approvalDate") LocalDateTime approvalDate);
	  

//	@Query("SELECT leaveApplications "
//	+ "from Employee e"
//	+ " WHERE e.teamId = :teamId group by e.name")
//List<LeaveApplication> findApplicationsByTeamId(@Param("teamId") Integer teamId);
	

	@Query("SELECT leaveApplications "
	+ "from Employee e"
	+ " WHERE e.teamId = :teamId")
List<LeaveApplication> findApplicationsByTeamId(@Param("teamId") Integer teamId);
	
	
	

@Query("SELECT e.leaveApplications from Employee e WHERE e.teamId = :teamId")
List<LeaveApplication> findLeaveHistoryByTeamId(@Param("teamId") Integer teamId);
}

