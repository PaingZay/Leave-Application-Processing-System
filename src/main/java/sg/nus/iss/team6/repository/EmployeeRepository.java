package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee, Integer> {
	  
	@Query("SELECT e "
			+ "from Team t"
			+ " JOIN t.EmployeesList e"
			+ " WHERE t.id = :tid")
	List<Employee> findEmployeeByTID(@Param("tid") int tid);
	
	@Query("SELECT e "
			+ "from Employee e"
			+ " WHERE e.name = :name")
	Employee findEmployeeByName(@Param("name") String name);

	@Query("SELECT r.id "
			+ "from Employee e"
			+ " JOIN e.role r"
			+ " WHERE r.id = :eid")
	Integer findEmployeeEmployeeRoleId(@Param("eid") Integer eid);
	

	  
	  
}
