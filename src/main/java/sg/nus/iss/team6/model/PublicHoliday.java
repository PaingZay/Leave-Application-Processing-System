package sg.nus.iss.team6.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import sg.nus.iss.team6.util.dayEnum;
import sg.nus.iss.team6.util.observanceStatus;

@Data
public class PublicHoliday {

	public PublicHoliday() {
		// TODO Auto-generated constructor stub
	}
	
	private Date date;
	private String name;
	
	@Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
	private dayEnum day;
	private Date observance;
	
	@Column(name = "observanceStrategy", nullable = false)
    @Enumerated(EnumType.STRING)
	private observanceStatus observanceStrategy;

}
