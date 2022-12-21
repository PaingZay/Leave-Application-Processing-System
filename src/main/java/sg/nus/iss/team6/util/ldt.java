package sg.nus.iss.team6.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class ldt {

	public static Boolean isValid(LocalDateTime ldtstart, LocalDateTime ldtend) {
		if (ldtstart.isBefore(ldtend)) {
			return true;
		}
		return false;
	}

	public static Boolean isOverlap(LocalDateTime ldt1start, LocalDateTime ldt1end, LocalDateTime ldt2start,
			LocalDateTime ldt2end) {

		// ensure valid date ranges
		if (ldt1start.isBefore(ldt1end) && ldt2start.isBefore(ldt2end)) {

			// No overlap logic
			if (ldt1start.isAfter(ldt2end) || ldt1end.isBefore(ldt2start)) {
				return false;
			}
		}
		return true;

	}

//	public static Duration getBalance(LocalDateTime ldt1start, LocalDateTime ldt1end, LocalDateTime ldt2start,
//			LocalDateTime ldt2end) {
//
//		Duration duration = null;
//
//		// test
//		if (isOverlap(ldt1start, ldt1end, ldt2start, ldt2end)) {
//
//			if (ldt2start.isBefore(ldt1end)) {
//				duration = Duration.between(ldt2start, ldt1end);
//			} else if (ldt1start.isBefore(ldt2end)) {
//				duration = Duration.between(ldt1start, ldt2end);
//			}
//
//		}
//		return duration;
//
//	}

	public static LocalDateTime setToLdtByHour(LocalDate ld, Integer hr) {

		// atTime is hrs, min, sec
		return ld.atTime(hr, 0, 0);
	}

	public static LocalDateTime setToLdtMidnight(LocalDate ld) {

		// atTime is hrs, min, sec
		return ld.atTime(0, 0, 0);
	}

	public static LocalDateTime setToLdt9am(LocalDate ld) {
		return ld.atTime(9, 0, 0);
	}

	public static LocalDateTime setToLdt6pm(LocalDate ld) {
		return ld.atTime(18, 0, 0);
	}

	public static long[] convertFromSeconds(long seconds) {

		long day = (int) TimeUnit.SECONDS.toDays(seconds);
		long hour = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
		long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
		long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
		long[] timeArr = new long[] { day, hour, minute, second };

		return timeArr;

	}

	public static long convertToSeconds(long[] timeArr) {

		long seconds = 0;
		seconds += timeArr[3]; // add seconds
		seconds += timeArr[2] * 60; // 1min = 60 sec
		seconds += timeArr[1] * 60 * 60; // 1hr = 60 sec * 60 min
		seconds += timeArr[0] * 24 * 60 * 60; // 1day = 60 sec * 60 min * 24 hrs

		return seconds;
	}

	public static String secondsToString(long seconds) {
		return timeArrToString(ldt.convertFromSeconds(seconds));
	}

	public static String timeArrToString(long[] timeArr) {

		String seg1 = timeArr[0] + " days, ";
		String seg2 = timeArr[1] + " hours, ";
		String seg3 = timeArr[2] + " minutes, ";
		String seg4 = timeArr[3] + " seconds";

		return seg1 + seg2 + seg3 + seg4;

	}

	//get a list of all saturdays
	public static List<LocalDateTime> getWeekendDates(LocalDateTime ldt1, LocalDateTime ldt2) {
		List<LocalDateTime> result = new ArrayList<LocalDateTime>();
		
		LocalDateTime start= ldt1;
		LocalDateTime end= ldt2;
		
		//ldt1.toLocalDate();
		
		for (LocalDateTime date = start; date.isBefore(end); date = date.plusDays(1)) {
			DayOfWeek day = date.getDayOfWeek();
			
			if (day == DayOfWeek.SATURDAY) {
				result.add(date.toLocalDate().atTime(0,0,0));
			}
		}
		return result;
	}
	
	//getbalancehours?
	//can't overlap >1 weekend
	public static long getOverlapInSeconds(LocalDateTime ldt1start, LocalDateTime ldt1end, LocalDateTime ldt2start,
			LocalDateTime ldt2end) {
		
		//to not return negative
		if(isOverlap(ldt1start, ldt1end, ldt2start,ldt2end)) {
			
			LocalDateTime minUpperBound;
			LocalDateTime maxLowerBound;
			
			if(ldt1end.isBefore(ldt2end)) {
				minUpperBound=ldt1end;
			}
			else minUpperBound=ldt2end;
			
			if(ldt1start.isAfter(ldt2start)) {
				maxLowerBound=ldt1start;
			}
			else maxLowerBound=ldt2start;
			
			long returnValue=ChronoUnit.SECONDS.between(maxLowerBound,minUpperBound);
			return returnValue;
		}
		
		return 0;

	}
	
	public static LocalDateTime getMin(LocalDateTime ldt1,LocalDateTime ldt2) {
		LocalDateTime min;
		if(ldt1.isBefore(ldt2)) {
			min=ldt1;
		}
		else min=ldt2;
		
		return min;
	}
	
	public static LocalDateTime getMax(LocalDateTime ldt1,LocalDateTime ldt2) {
		LocalDateTime max;
		if(ldt1.isAfter(ldt2)) {
			max=ldt1;
		}
		else max=ldt2;
		
		return max;
	}
	
	public static long getUnixTimeStampSG(LocalDateTime ldt) {
		ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(8));
		long epoch = ldt.atZone(zoneId).toEpochSecond();
		return epoch;

	}
	
	public static LocalDateTime getUnixTimeStampSGInLdt(long seconds) {
		ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(8));
		long milliseconds=seconds*1000;
		LocalDateTime toReturn=LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), zoneId);
		return toReturn;
	}
	
}
