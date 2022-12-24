package sg.nus.iss.team6.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	  @Query("SELECT r.name FROM Role r")
	  List<String> findAllRolesNames();
	  
	  @Query("SELECT r FROM Role r WHERE r.name = :name")
	  List<Role> findRoleByName(@Param("name") String name);

	  @Query("SELECT leaveTypes "
			+ "from Role r"
			+ " WHERE r.Id = :Id")
	List<LeaveType> findLeaveTypesByRole(@Param("Id") int Id);
	}
