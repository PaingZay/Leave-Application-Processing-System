package sg.nus.iss.team6.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PUBLIC_HOLIDAY") 
public class PublicHoliday {

	@Id
	@Column(name="publicHolidayId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer phDay;
	
	@Column(nullable = false)
	private Integer phMonth;
	
	@Column(name = "active_status", nullable = false)
	private boolean active;
	
	
	public PublicHoliday() {}


	public PublicHoliday(String name, Integer day, Integer month) {
		this.name = name;
		this.phDay = day;
		this.phMonth = month;
		
		this.active=true;
	}



	public LocalDateTime getLDTByYear(Integer year) {
		LocalDate date = LocalDate.of(year, this.phMonth, this.phDay);
		LocalTime time = LocalTime.of(0,0);
		return LocalDateTime.of(date,time);
	}
	
	public DayOfWeek getPHDay (Integer year) {
		LocalDate date = LocalDate.of(year, this.phMonth, this.phDay);
		return date.getDayOfWeek();
	}
	
	public Integer getPHDayIndex (Integer year) {
		LocalDate date = LocalDate.of(year, this.phMonth, this.phDay);
		DayOfWeek day = date.getDayOfWeek();
	    return day.getValue();
	}

}
