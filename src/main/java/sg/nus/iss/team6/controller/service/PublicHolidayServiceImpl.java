package sg.nus.iss.team6.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.repository.EmployeeRepository;
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
