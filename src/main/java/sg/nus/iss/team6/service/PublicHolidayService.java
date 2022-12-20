package sg.nus.iss.team6.service;

import java.util.List;

import sg.nus.iss.team6.model.PublicHoliday;

public interface PublicHolidayService {

	List<PublicHoliday> findAllHoliday();
	PublicHoliday findPublicHoliday(Integer id);
	PublicHoliday createPublicHoliday(PublicHoliday ph);
	PublicHoliday changePublicHoliday(PublicHoliday ph);
	void removePublicHoliday(PublicHoliday ph);
	PublicHoliday findPublicHolidayByName(String name);
}
