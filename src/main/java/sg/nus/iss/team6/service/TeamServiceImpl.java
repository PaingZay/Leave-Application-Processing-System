package sg.nus.iss.team6.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.model.Team;
import sg.nus.iss.team6.repository.RoleRepository;
import sg.nus.iss.team6.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService{
		@Resource
		private TeamRepository teamRepository;
		

		@Transactional
		@Override
		public Team findTeam(Integer TID) {
			return teamRepository.findById(TID).orElse(null);
		}


	
}
