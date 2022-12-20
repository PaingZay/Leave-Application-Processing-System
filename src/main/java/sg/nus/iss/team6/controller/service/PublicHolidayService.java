package sg.nus.iss.team6.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.PublicHoliday;

@Service
public interface PublicHolidayService {

	  List<PublicHoliday> findAllPublicHolidays();

}
