package sg.nus.iss.team6.service;

import java.util.List;
import org.springframework.stereotype.Service;
import sg.nus.iss.team6.model.PublicHoliday;


public interface PublicHolidayService {

	  List<PublicHoliday> findAllPublicHolidays();

}
