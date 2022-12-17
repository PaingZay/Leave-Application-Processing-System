package sg.nus.iss.team6.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ldt {
	
	public static Boolean isValid(LocalDateTime ldtstart, LocalDateTime ldtend) {
		if(ldtstart.isBefore(ldtend)){
			return true;
		}
		return false;
	}
	
	public static Boolean isOverlap(LocalDateTime ldt1start, LocalDateTime ldt1end,LocalDateTime ldt2start, LocalDateTime ldt2end) {
		
		//ensure valid date ranges
		if(ldt1start.isBefore(ldt1end) && ldt2start.isBefore(ldt2end)) {
			
			if (ldt1start.isAfter(ldt2end) && ldt1end.isBefore(ldt2start)) {
				return true;
				
			}
		}
		
		return false;
	}
	
	public static Duration getBalance(LocalDateTime ldt1start, LocalDateTime ldt1end,LocalDateTime ldt2start, LocalDateTime ldt2end) {
		
		Duration duration = null;
		
		//test
		if (isOverlap(ldt1start,ldt1end,ldt2start, ldt2end)) {
			
			if(ldt2start.isBefore(ldt1end)) {
				duration = Duration.between(ldt2start, ldt1end);
			}
			else if(ldt1start.isBefore(ldt2end)) {
				duration = Duration.between(ldt1start, ldt2end);
			}
			
		}
		return duration;
		
	}
	
	public static LocalDateTime setToLdtMidnight(LocalDate ld) {
		
		//atTime is hrs, min, sec
		return ld.atTime(0,0,0);
		
	}

}
