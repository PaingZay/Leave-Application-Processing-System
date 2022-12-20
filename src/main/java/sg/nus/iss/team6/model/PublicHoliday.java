package sg.nus.iss.team6.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Column(nullable = false)
	@Min(value=1)
	@Max(value=31)
	private Integer phDay;
	
	@Column(nullable = false)
	@Min(value=1)
	@Max(value=12)
	private Integer phMonth;
	
	@Max(value=15)
	@Column(nullable = false)
	private Integer phLength;
	
	@Column(name = "active_status", nullable = false)
	private boolean active;
	
	
	public PublicHoliday() {}


	public PublicHoliday(String name, Integer day, Integer month, Integer phLength) {
		this.name = name;
		this.phDay = day;
		this.phMonth = month;
		this.phLength = phLength;
		
		this.active=true;
	}
	

}
