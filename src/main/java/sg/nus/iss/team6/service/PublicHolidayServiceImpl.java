package sg.nus.iss.team6.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.repository.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService{
	  @Resource
	  private PublicHolidayRepository phRepository;
	  
	  @Override
	  public List<PublicHoliday> findAllPublicHolidays(){
		  return phRepository.findAll();
	  }
	
}
