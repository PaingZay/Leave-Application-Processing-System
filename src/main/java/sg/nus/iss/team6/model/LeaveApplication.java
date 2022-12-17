package sg.nus.iss.team6.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import sg.nus.iss.team6.controller.service.EmployeeService;
import sg.nus.iss.team6.controller.service.EmployeeServiceImpl;
import sg.nus.iss.team6.util.LeaveTypeStatus;
import sg.nus.iss.team6.util.ldt;

@Data
@Entity
public class LeaveApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//employeeId FK -int
	@Column(nullable = false)
	private LocalDateTime applicationDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime approvalDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime leaveStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime leaveEndDate;
	
	//workDelegates refers to which colleagues the leave applicant has deferred their tasks to
	@ManyToOne
	private Employee workDelegate;
	
	private String overseasPhone;
	//leaveTypeId FK -int
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveTypeStatus status;
	
	private String comment;
	
	@Column(nullable = false)
	private String reason;
	
	@Column(nullable = false)
	private Boolean active;
	
	@ManyToOne
	private LeaveType leavetype;
	
	
	
	
	//--Getters and setters----

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}


	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	

	public LocalDateTime getLeaveStartDate() {
		return leaveStartDate;
	}


	public void setLeaveStartDate(LocalDateTime leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}


	public LocalDateTime getLeaveEndDate() {
		return leaveEndDate;
	}


	public void setLeaveEndDate(LocalDateTime leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}


	public Employee getWorkDelegate() {
		return workDelegate;
	}


	public void setWorkDelegate(Employee workDelegate) {
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


	public void setStatus(LeaveTypeStatus status) {
		this.status=status;
	}


	public void setActive(boolean b) {
		this.active=b;
		
	}
	
	//---Constructors-----
	
	public LeaveApplication() {}


	public LeaveApplication(LocalDateTime applicationDate, LocalDateTime leaveStartDate, LocalDateTime leaveEndDate,
			String reason, LeaveType leavetype) {
		
		this.applicationDate = applicationDate;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.reason = reason;
		this.leavetype = leavetype;
		
		this.status=LeaveTypeStatus.APPLIED;
		this.active=true;
	}


	//use this constructor for creating leaveApplications
	public LeaveApplication(LocalDateTime applicationDate, LocalDateTime leaveStartDate, LocalDateTime leaveEndDate,
			Employee workDelegate, String overseasPhone, String reason,LeaveType leavetype) {
		
		this.applicationDate = applicationDate;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.workDelegate = workDelegate;
		this.overseasPhone = overseasPhone;
		this.reason = reason;
		this.leavetype = leavetype;
		
		this.status=LeaveTypeStatus.APPLIED;
		this.active=true;
	}
	
	//update with form input, rather than static method
	public void updateUsingForm(LeaveAppForm leaveAppForm, LocalDateTime inputTime,LeaveType lt) {
		
		EmployeeServiceImpl eService=new EmployeeServiceImpl();
		Employee desiredEmployee=eService.findEmployeeByName(leaveAppForm.getWorkDelegate());
		
		//return null for invalid application
	    if(!ldt.isValid(leaveAppForm.getLeaveStartDate().atTime(0,0,0), leaveAppForm.getLeaveEndDate().atTime(0,0,0))) {
	    	return;
	    }
			this.applicationDate = inputTime;
			this.leaveStartDate = leaveAppForm.getLeaveStartDate().atTime(0,0,0);
			this.leaveEndDate = leaveAppForm.getLeaveEndDate().atTime(0,0,0);
			this.workDelegate = desiredEmployee;
			this.overseasPhone = leaveAppForm.getOverseasPhone();
			this.reason = leaveAppForm.getReason();
			this.leavetype = lt;
			
			this.status=LeaveTypeStatus.UPDATED;	
	}
	
	
	
	//---Override ToString---

	@Override
	public String toString() {
	  
	  return "Leave Application [id = " + id + ", name = " + reason + "]";
	}
	
	
	
	//remap leave start and end?
	//autogen getters/setters
}