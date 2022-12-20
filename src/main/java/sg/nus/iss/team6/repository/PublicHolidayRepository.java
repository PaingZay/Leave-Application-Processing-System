package sg.nus.iss.team6.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.team6.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer>{

	@Query("SELECT ph from PublicHoliday ph WHERE ph.id = :id ")
	PublicHoliday findPublicHolidayById(@Param("id") Integer id);
	
	@Query("SELECT ph from PublicHoliday ph WHERE ph.name = :name")
	PublicHoliday findHolidayByName(@Param("name") String name);
}
