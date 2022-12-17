package sg.nus.iss.team6.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import sg.nus.iss.team6.util.LeaveTypeStatus;

public class LeaveAppForm {

	//private LocalDateTime applicationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveEndDate;
	
	private String workDelegate;
	
	private String overseasPhone;
	
	private String reason;
	
	
	
	
	//private LeaveType leavetype;

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getWorkDelegate() {
		return workDelegate;
	}

	public void setWorkDelegate(String workDelegate) {
		this.workDelegate = workDelegate;
	}

	public String getOverseasPhone() {
		return overseasPhone;
	}

	public void setOverseasPhone(String overseasPhone) {
		this.overseasPhone = overseasPhone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	//constructors-----------
	public LeaveAppForm() {
	}

	public LeaveAppForm(LocalDate leaveStartDate, LocalDate leaveEndDate, String workDelegate, String overseasPhone,
			String reason) {
		//missing applicationDate and leaveType
		//just consume endpoint input
		
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.workDelegate = workDelegate;
		this.overseasPhone = overseasPhone;
		this.reason = reason;
	}
	
	public LeaveApplication convertToLA(LocalDateTime applicationDate,LeaveType leavetype) {
		
		return null;
		
//		LeaveApplication returnThis = new LeaveApplication(applicationDate,leaveStartDate,leaveEndDate,
//				workDelegate, overseasPhone, reason, leavetype);
		
	}
	
	
	@Override
	public String toString() {
	  
	  return "Leave Application [leaveStartDate = " + leaveStartDate + ", leaveEndDate = " + leaveEndDate+ ", workDelegate = " +workDelegate + ", overseasPhone = " +overseasPhone + ", reason = " + reason+ "]";
	}
	
	

}
