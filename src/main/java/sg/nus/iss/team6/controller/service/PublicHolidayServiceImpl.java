package sg.nus.iss.team6.controller.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.repository.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
	@Resource
	private PublicHolidayRepository phRepository;

	@Override
	public List<PublicHoliday> findAllPublicHolidays() {
		return phRepository.findAll();
	}

	@Override
	public LocalDateTime getLDTCurrYear(PublicHoliday ph) {
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		LocalDate date = LocalDate.of(currYear, ph.getPhMonth(), ph.getPhDay());
		LocalTime time = LocalTime.of(0, 0);
		return LocalDateTime.of(date, time);
	}
	
	@Override
	public LocalDateTime getLDTEndCurrYear(PublicHoliday ph) {

		LocalDateTime start = getLDTCurrYear(ph);
		return start.plusDays(ph.getPhLength());
	}
	
	@Override
	public LocalDateTime getLDTByYear(PublicHoliday ph, Integer year) {
		LocalDate date = LocalDate.of(year, ph.getPhMonth(), ph.getPhDay());
		LocalTime time = LocalTime.of(0, 0);
		return LocalDateTime.of(date, time);
	}

	@Override
	public LocalDateTime getLDTEndByYear(PublicHoliday ph,Integer year) {

		LocalDateTime start = getLDTByYear(ph,year);
		return start.plusDays(ph.getPhLength());
	}

	@Override
	public DayOfWeek getPHDay(PublicHoliday ph,Integer year) {
		LocalDate date = LocalDate.of(year, ph.getPhMonth(), ph.getPhDay());
		return date.getDayOfWeek();
	}

	@Override
	public Integer getPHDayIndex(PublicHoliday ph,Integer year) {
		LocalDate date = LocalDate.of(year, ph.getPhMonth(), ph.getPhDay());
		DayOfWeek day = date.getDayOfWeek();
		return day.getValue();
	}

}
