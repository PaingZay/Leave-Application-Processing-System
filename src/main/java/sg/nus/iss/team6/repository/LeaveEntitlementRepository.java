package sg.nus.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.LeaveEntitlement;

@Repository
public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Long> {

}
