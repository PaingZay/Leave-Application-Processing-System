package sg.nus.iss.team6.controller.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.PublicHoliday;

@Service
public interface PublicHolidayService {

	  List<PublicHoliday> findAllPublicHolidays();

		
		public LocalDateTime getLDTCurrYear(PublicHoliday ph);
		
		public LocalDateTime getLDTEndCurrYear(PublicHoliday ph);
		
		public LocalDateTime getLDTByYear(PublicHoliday ph, Integer year);

		public LocalDateTime getLDTEndByYear(PublicHoliday ph, Integer year);
		
		public DayOfWeek getPHDay(PublicHoliday ph, Integer year);

		public Integer getPHDayIndex(PublicHoliday ph, Integer year);
}
