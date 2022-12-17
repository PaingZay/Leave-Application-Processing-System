package sg.nus.iss.team6.service;

//import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;



@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

  @Autowired
  LeaveApplicationRepository applicationRepository;

  @Transactional
  public void addBooking(LeaveApplication application) {
    applicationRepository.save(application);
  }
  
  @Override
  public List<LeaveApplication> findAllApplication() {
    return applicationRepository.findAll();
  }
  
  @Override
  @Transactional
  public LeaveApplication findApplication(Integer id) {
    return applicationRepository.findById(id).orElse(null);
  }
  
  @Override
  @Transactional
  public LeaveApplication changeApplication(LeaveApplication application) {
    return applicationRepository.saveAndFlush(application);
  }

}
