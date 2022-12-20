package sg.nus.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
