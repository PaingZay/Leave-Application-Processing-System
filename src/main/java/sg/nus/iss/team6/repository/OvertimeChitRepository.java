package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.OvertimeChit;

@Repository
public interface OvertimeChitRepository extends JpaRepository< OvertimeChit, Integer> {
	  

	@Query("SELECT e.overtimeChits "
			+ "from Employee e"
			+ " WHERE e.id = :eid")
	List<OvertimeChit> findOvertimechitsByEmployee(@Param("eid") int eid);
	  
}
