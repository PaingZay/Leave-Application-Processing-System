package sg.nus.iss.team6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;

@Repository
public interface RoleRepository extends JpaRepository< Role, Integer>{
	@Query("SELECT leaveTypes "
			+ "from Role r"
			+ " WHERE r.id = :rid")
	List<LeaveType> findLeaveTypesByRole(@Param("rid") int rid);
}
