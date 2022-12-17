package sg.nus.iss.laps.service;

//import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.nus.iss.laps.model.Application;
import sg.nus.iss.laps.repository.ApplicationRepository;



@Service
public class ApplicationServiceImpl implements ApplicationService {

  @Autowired
  ApplicationRepository applicationRepository;

  @Transactional
  public void addBooking(Application application) {
    applicationRepository.save(application);
  }
  
  @Override
  public List<Application> findAllApplication() {
    return applicationRepository.findAll();
  }
  
  @Override
  @Transactional
  public Application findApplication(Integer id) {
    return applicationRepository.findById(id).orElse(null);
  }
  
  @Override
  @Transactional
  public Application changeApplication(Application application) {
    return applicationRepository.saveAndFlush(application);
  }

}
