package sg.nus.iss.team6.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import sg.nus.iss.team6.service.EmployeeService;

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

	private Integer leaveId;
	
	
	
	
	//private LeaveType leavetype;
	
	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

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

//	public Integer getApplicantId() {
//		return applicantId;
//	}
//
//	public void setApplicantId(Integer applicantId) {
//		this.applicantId = applicantId;
//	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
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
	

//	public LeaveApplication convertToLA(LocalDateTime applicationDate,LeaveType leavetype,Employee desiredEmployee) {
//
//
//		LeaveApplication myLA = new LeaveApplication();
//		
//		myLA.setApplicationDate(applicationDate);
//		myLA.setApprovalDate(null);
//		myLA.setLeaveStartDate(this.leaveStartDate.atTime(this.leaveStartTime,0,0));
//		myLA.setLeaveEndDate(this.leaveEndDate.atTime(this.leaveEndTime,0,0));
//		myLA.setWorkDelegate(desiredEmployee);
//		myLA.setOverseasPhone(this.overseasPhone);
//		myLA.setStatus(ApplicationStatus.APPLIED);
//		myLA.setComment(null);
//		myLA.setReason(this.reason);
//		myLA.setActive(true);
//		myLA.setLeaveType(leavetype);
//		
//		return myLA;
//	}
//	
//
//	public LeaveApplication convertToLA(LocalDateTime applicationDate,LeaveType leavetype,Employee desiredEmployee,LocalDateTime newStart,LocalDateTime newEnd) {
//		
//		LeaveApplication myLA = new LeaveApplication();
//		
//		myLA.setApplicationDate(applicationDate);
//		myLA.setApprovalDate(null);
//		myLA.setLeaveStartDate(newStart);
//		myLA.setLeaveEndDate(newEnd);
//		myLA.setWorkDelegate(desiredEmployee);
//		myLA.setOverseasPhone(this.overseasPhone);
//		myLA.setStatus(ApplicationStatus.APPLIED);
//		myLA.setComment(null);
//		myLA.setReason(this.reason);
//		myLA.setActive(true);
//		myLA.setLeaveType(leavetype);
//		
//		return myLA;
//	}
//	
	//final to hook to Paing's part
	public LeaveApplication convertToLA(Employee employee, LocalDateTime applicationDate,LeaveType leavetype,Employee desiredEmployee,LocalDateTime newStart,LocalDateTime newEnd) {
		
		LeaveApplication myLA = new LeaveApplication();
		
		LocalTime startTime=LocalTime.of(leaveStartTime, 0);
		LocalTime endTime=LocalTime.of(leaveEndTime, 0);
		LocalDateTime originalStart = leaveStartDate.atTime(leaveStartTime,0,0);
		LocalDateTime originalEnd = leaveEndDate.atTime(leaveEndTime,0,0);
		long secondsBetween= (long) Duration.between(
				originalStart, 
				originalEnd)
				.toSeconds();
		
		myLA.setEmployee(employee);
		myLA.setApplicationDate(applicationDate);
		myLA.setLeaveStartDate(newStart);
		myLA.setLeaveEndDate(newEnd);
		
		//new
		myLA.setLeaveDuration(secondsBetween);
		
		myLA.setApprovalDate(null);
		myLA.setLeaveType(leavetype);
		myLA.setComment(null);
		myLA.setReason(this.reason);
		myLA.setActive(true);
		
		//new
		myLA.setWorkDelegate(desiredEmployee);
		myLA.setOverseasPhone(this.overseasPhone);
		myLA.setStatus(ApplicationStatus.APPLIED);

		
		
		return myLA;
	}
	
	@Override
	public String toString() {
	  
	  return "Leave Application [leaveStartDate = " + leaveStartDate + ", leaveEndDate = " + leaveEndDate+ ", workDelegate = " +workDelegate + ", overseasPhone = " +overseasPhone + ", reason = " + reason+ "]";
	}
	
	

}
