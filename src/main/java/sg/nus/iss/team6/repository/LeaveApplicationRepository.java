package sg.nus.iss.team6.repository;

//import java.time.LocalDate;
//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import sg.nus.iss.team6.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	
//	   @Query("Select la from leaveApplication la where :date between la.leaveStartDate AND la.leaveEndDate AND la.facility.id = :fid")
//	   public List<Application> findBooking(@Param("date") LocalDate date, @Param("fid") Integer fid);
	  
	
}