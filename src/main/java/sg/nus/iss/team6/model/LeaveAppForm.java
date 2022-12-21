package sg.nus.iss.team6.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import sg.nus.iss.team6.controller.service.EmployeeService;
import sg.nus.iss.team6.util.ApplicationStatus;

public class LeaveAppForm {
	@Autowired
	private EmployeeService eService;

	//private LocalDateTime applicationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveEndDate;
	
	private Integer leaveStartTime;
	
	private Integer leaveEndTime;
	
	private String workDelegate;
	
	private String overseasPhone;
	
	private String reason;
	
	//important!---------------
	
	private String leaveTypeName;
	
	private String applicantId;
	
	
	
	
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
	
	

	public Integer getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(Integer leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public Integer getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(Integer leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
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
	

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public Integer getApplicantId() {
		return Integer.valueOf(applicantId);
	}

	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId.toString();
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

	

	public LeaveApplication convertToLA(LocalDateTime applicationDate,LeaveType leavetype,Employee desiredEmployee,LocalDateTime newEnd) {
		
		long secondsBetween= (long) Duration.between(this.leaveStartDate.atTime(this.leaveStartTime,0,0), this.leaveEndDate.atTime(this.leaveEndTime,0,0)).toSeconds();
		LeaveApplication myLA = new LeaveApplication();
		
		myLA.setApplicationDate(applicationDate);
		myLA.setApprovalDate(null);
		myLA.setLeaveStartDate(this.leaveStartDate.atTime(this.leaveStartTime,0,0));
		
		if(newEnd==null) {
			myLA.setLeaveEndDate(this.leaveEndDate.atTime(this.leaveEndTime,0,0));
		}
		else {
			myLA.setLeaveEndDate(newEnd);
		}
		
		myLA.setLeaveDuration(secondsBetween);
		
		myLA.setWorkDelegate(desiredEmployee);
		myLA.setOverseasPhone(this.overseasPhone);
		myLA.setStatus(ApplicationStatus.APPLIED);
		myLA.setComment(null);
		myLA.setReason(this.reason);
		myLA.setActive(true);
		myLA.setLeaveType(leavetype);
		
		return myLA;
	}
	
	@Override
	public String toString() {
	  
	  return "Leave Application [leaveStartDate = " + leaveStartDate + ", leaveEndDate = " + leaveEndDate+ ", workDelegate = " +workDelegate + ", overseasPhone = " +overseasPhone + ", reason = " + reason+ "]";
	}
	
	

}
