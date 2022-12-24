package sg.nus.iss.team6.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
	
	@Min(value=1)
	@Max(value=31)
	@Column(nullable = false)
	private Integer phDay;
	
	@Min(value=1)
	@Max(value=12)
	@Column(nullable = false)
	private Integer phMonth;
	
	@Max(value=15)
	@Column(nullable = false)
	private Integer phLength;
	
	@Column(name = "active_status", nullable = false)
	private boolean active;
	
	
	//Getters/setters
	
	public int getId() {	
		return id;	
	}	
	public void setId(int id) {	
		this.id = id;	
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPhDay() {
		return phDay;
	}


	public void setPhDay(Integer phDay) {
		this.phDay = phDay;
	}


	public Integer getPhMonth() {
		return phMonth;
	}


	public void setPhMonth(Integer phMonth) {
		this.phMonth = phMonth;
	}


	public Integer getPhLength() {
		return phLength;
	}


	public void setPhLength(Integer phLength) {
		this.phLength = phLength;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	//Constructors
	public PublicHoliday() {}



	public PublicHoliday(String name, Integer day, Integer month, Integer phLength) {
		this.name = name;
		this.phDay = day;
		this.phMonth = month;
		this.phLength = phLength;
		
		this.active=true;
	}
	
	
	

	
	
	
	
	//Methods

	public LocalDateTime getLDTCurrYear() {
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		LocalDate date = LocalDate.of(currYear, this.phMonth, this.phDay);
		LocalTime time = LocalTime.of(0,0);
		return LocalDateTime.of(date,time);
	}
	
	public LocalDateTime getLDTEndCurrYear() {
		
		LocalDateTime start=getLDTCurrYear();
		return start.plusDays(getPhLength());
	}
	
	public LocalDateTime getLDTByYear(Integer year) {
		LocalDate date = LocalDate.of(year, this.phMonth, this.phDay);
		LocalTime time = LocalTime.of(0,0);
		return LocalDateTime.of(date,time);
	}
	
	public LocalDateTime getLDTEndByYear(Integer year) {
		
		LocalDateTime start=getLDTByYear(year);
		return start.plusDays(getPhLength());
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
