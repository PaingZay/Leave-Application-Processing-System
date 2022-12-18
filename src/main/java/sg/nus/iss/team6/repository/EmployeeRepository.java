package sg.nus.iss.team6.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.team6.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//  @Query("Select m from Member m where m.firstName LIKE :name")
//  List<Employee> findMemberByFirstName(@Param("name") String name);
	
	@Query("SELECT u FROM Employee u WHERE u.name=:username AND u.password=:pwd")
	  Employee findUserByNamePwd(@Param("username")String username, @Param("pwd")String pwd);

//	@Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.employeeId = e2.managerId AND e1.employeeId = :eid")
//	  List<Employee> findSubordinates(@Param("eid") int tid);//Not understand very well What is selecting subordinates I just want to know
	
	@Query("SELECT DISTINCT e FROM Employee e where Team_id=:tid")
	List<Employee> findSubordinates(@Param("tid") int tid);
	
	@Query("SELECT e FROM Employee e where e.username = :username AND e.password = :password")
	public Employee findByName(@Param("username") String username, @Param ("password") String password);
		
	@Query("SELECT e FROM Employee e where e.username = :username")
	public Employee findUser(@Param("username") String username);
}
