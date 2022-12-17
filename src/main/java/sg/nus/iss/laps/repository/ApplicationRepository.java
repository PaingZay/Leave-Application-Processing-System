package sg.nus.iss.laps.repository;

//import java.time.LocalDate;
//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import sg.nus.iss.laps.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
//	   @Query("Select la from leaveApplication la where :date between la.leaveStartDate AND la.leaveEndDate AND la.facility.id = :fid")
//	   public List<Application> findBooking(@Param("date") LocalDate date, @Param("fid") Integer fid);
	  
	
}