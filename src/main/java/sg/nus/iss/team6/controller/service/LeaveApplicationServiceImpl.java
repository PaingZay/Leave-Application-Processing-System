package sg.nus.iss.team6.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;


@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
  
  @Resource
  private LeaveApplicationRepository laRepository;
  

  @Override
  public List<LeaveApplication> findAllLeaveApplications() {
    return laRepository.findAll();
  }


  @Override
  @Transactional
  public LeaveApplication findLeaveApplication(Integer laid) {
    return laRepository.findById(laid).orElse(null);
  }

  @Override
  @Transactional
  public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) {
    return laRepository.saveAndFlush(leaveApplication);
  }

  @Override
  @Transactional
  public LeaveApplication changeLeaveApplication(LeaveApplication LeaveApplication) {
    return laRepository.saveAndFlush(LeaveApplication);
  }

  @Override
  @Transactional
  public void removeLeaveApplication(LeaveApplication LeaveApplication) {
	  LeaveApplication.setActive(false);
  }

//  @Override
//  @Transactional
//  public List<LeaveApplication> findLeaveApplicationByEID(int eid) {
//	//adding comments in leaveApplicationService can cause this to bug out
//    return laRepository.findLeaveApplicationByEID(eid);
//  }
 
}