package sg.nus.iss.team6.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;



@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

  @Resource
  LeaveApplicationRepository leaveApplicationRepository;

  @Transactional
  public void addBooking(LeaveApplication application) {
    leaveApplicationRepository.save(application);
  }
  
  @Override
  public List<LeaveApplication> findAllApplication() {
    return leaveApplicationRepository.findAll();
  }
  
  @Override
  @Transactional
  public LeaveApplication findApplication(Integer id) {
    return leaveApplicationRepository.findById(id).orElse(null);
  }
  
  @Override
  @Transactional
  public LeaveApplication changeApplication(LeaveApplication application) {
    return leaveApplicationRepository.saveAndFlush(application);
  }

  @Override
  public List<LeaveApplication> findAllLeaveApplications() {
    return leaveApplicationRepository.findAll();
  }


  @Override
  @Transactional
  public LeaveApplication findLeaveApplication(Integer laid) {
    return leaveApplicationRepository.findById(laid).orElse(null);
  }

  @Override
  @Transactional
  public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) {
    return leaveApplicationRepository.saveAndFlush(leaveApplication);
  }

  @Override
  @Transactional
  public LeaveApplication changeLeaveApplication(LeaveApplication LeaveApplication) {
    return leaveApplicationRepository.saveAndFlush(LeaveApplication);
  }

  @Override
  @Transactional
  public void removeLeaveApplication(LeaveApplication LeaveApplication) {
	  LeaveApplication.setActive(false);
  }

  @Override
  @Transactional
  public void updateApplicationById(Integer id,String cmt, ApplicationStatus status, LocalDateTime approvalDate)
  {
      leaveApplicationRepository.updateApplicationById(id,cmt,status,approvalDate);
  }

  @Override
	  @Transactional
	  public List<LeaveApplication> findApplicationsByTeamId(Integer teamId){
		  return leaveApplicationRepository.findApplicationsByTeamId(teamId);
	  }

    @Override
	  @Transactional
    public List<LeaveApplication> findLeaveHistoryByTeamId(Integer teamId){
      return leaveApplicationRepository.findLeaveHistoryByTeamId(teamId);
    }

}
