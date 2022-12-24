package sg.nus.iss.team6.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LeaveApplication
{
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Employee employee;

	// @NotNull(message = "Application date must be provided")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime applicationDate;
	
	// @NotNull(message = "Start date must be provided")
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime leaveStartDate;
	
	// @NotNull(message = "End date must be provided")
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime leaveEndDate;

	@Column(nullable = false)
	private long leaveDuration;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime approvalDate;
	
	@ManyToOne
	@NotNull(message = "End date must be provided")
	private LeaveType leaveType;
	
	private String comment;

	private String reason;
	
	private Boolean active;

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne
	private Employee workDelegate;

	private String overseasPhone;
	
	
	@Column(name = "status", columnDefinition = "ENUM('APPLIED','UPDATED','CANCELLED','APPROVED','REJECTED')")
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	public LeaveApplication(){}
	public LeaveApplication(Employee employee,LocalDateTime applicationDate,LocalDateTime leaveStartDate,LocalDateTime leaveEndDate, LocalDateTime approvalDate, LeaveType leaveType, String comment, String reason, boolean active, ApplicationStatus status, Employee workDelegate, String overseasPhone)
	{
		this.employee = employee;
		this.applicationDate = applicationDate;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.approvalDate = approvalDate;
		this.leaveType = leaveType;
		this.comment = comment;
		this.reason = reason;
		this.active = active;
		this.status = status;
		this.workDelegate = workDelegate;
		this.overseasPhone = overseasPhone;
	}
	public LeaveApplication(LocalDateTime applicationDate, LocalDateTime leaveStartDate, LocalDateTime leaveEndDate,
			String reason, LeaveType leavetype) {
		
		this.applicationDate = applicationDate;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.reason = reason;
		this.leaveType = leavetype;
		this.status=ApplicationStatus.APPLIED;
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
		this.leaveType = leavetype;
		
		this.status=ApplicationStatus.APPLIED;
		this.active=true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
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

	public long getLeaveDuration() {
		return leaveDuration;
	}


	public void setLeaveDuration(long leaveDuration) {
		this.leaveDuration = leaveDuration;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	
}

// @Data
// @Entity
// public class LeaveApplication {
	
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int id;
// 	//employeeId FK -int
// 	@Column(nullable = false)
// 	private LocalDateTime applicationDate;
	
// 	@DateTimeFormat(pattern = "yyyy-MM-dd")
// 	private LocalDateTime approvalDate;
	
// 	@DateTimeFormat(pattern = "yyyy-MM-dd")
// 	@Column(nullable = false)
// 	private LocalDateTime leaveStartDate;
	
// 	@DateTimeFormat(pattern = "yyyy-MM-dd")
// 	@Column(nullable = false)
// 	private LocalDateTime leaveEndDate;
	
// 	//workDelegates refers to which colleagues the leave applicant has deferred their tasks to
// 	@ManyToOne
// 	private Employee workDelegate;
	
// 	private String overseasPhone;
// 	//leaveTypeId FK -int
	
// 	@Column(nullable = false)
//     @Enumerated(EnumType.STRING)
//     private LeaveTypeStatus status;
	
// 	private String comment;
	
// 	@Column(nullable = false)
// 	private String reason;
	
// 	@Column(nullable = false)
// 	private Boolean active;
	
// 	@ManyToOne
// 	private LeaveType leaveType;
	
	
	
	
// 	//--Getters and setters----
	
// 	public int getId() {
// 		return id;
// 	}


// 	public void setId(int id) {
// 		this.id = id;
// 	}

// 	public LocalDateTime getApplicationDate() {
// 		return applicationDate;
// 	}


// 	public void setApplicationDate(LocalDateTime applicationDate) {
// 		this.applicationDate = applicationDate;
// 	}
	

// 	public LocalDateTime getApprovalDate() {
// 		return approvalDate;
// 	}


// 	public void setApprovalDate(LocalDateTime approvalDate) {
// 		this.approvalDate = approvalDate;
// 	}

// 	public LocalDateTime getLeaveStartDate() {
// 		return leaveStartDate;
// 	}


// 	public void setLeaveStartDate(LocalDateTime leaveStartDate) {
// 		this.leaveStartDate = leaveStartDate;
// 	}


// 	public LocalDateTime getLeaveEndDate() {
// 		return leaveEndDate;
// 	}


// 	public void setLeaveEndDate(LocalDateTime leaveEndDate) {
// 		this.leaveEndDate = leaveEndDate;
// 	}


// 	public Employee getWorkDelegate() {
// 		return workDelegate;
// 	}


// 	public void setWorkDelegate(Employee workDelegate) {
// 		this.workDelegate = workDelegate;
// 	}


// 	public String getOverseasPhone() {
// 		return overseasPhone;
// 	}


// 	public void setOverseasPhone(String overseasPhone) {
// 		this.overseasPhone = overseasPhone;
// 	}

// 	public void setStatus(LeaveTypeStatus status) {
// 		this.status=status;
// 	}
	
// 	public LeaveTypeStatus getStatus() {
// 		return status;
// 	}


// 	public String getReason() {
// 		return reason;
// 	}
	
// 	public String getComment() {
// 		return comment;
// 	}


// 	public void setComment(String comment) {
// 		this.comment = comment;
// 	}

// 	public void setReason(String reason) {
// 		this.reason = reason;
// 	}


// 	public Boolean getActive() {
// 		return active;
// 	}


// 	public void setActive(Boolean active) {
// 		this.active = active;
// 	}

// 	public LeaveType getLeaveType() {
// 		return leaveType;
// 	}


// 	public void setLeaveType(LeaveType leavetype) {
// 		this.leaveType = leavetype;
// 	}





	
	
// 	//---Constructors-----
	
// 	public LeaveApplication() {}


// 	public LeaveApplication(LocalDateTime applicationDate, LocalDateTime leaveStartDate, LocalDateTime leaveEndDate,
// 			String reason, LeaveType leavetype) {
		
// 		this.applicationDate = applicationDate;
// 		this.leaveStartDate = leaveStartDate;
// 		this.leaveEndDate = leaveEndDate;
// 		this.reason = reason;
// 		this.leaveType = leavetype;
		
// 		this.status=LeaveTypeStatus.APPLIED;
// 		this.active=true;
// 	}


// 	//use this constructor for creating leaveApplications
// 	public LeaveApplication(LocalDateTime applicationDate, LocalDateTime leaveStartDate, LocalDateTime leaveEndDate,
// 			Employee workDelegate, String overseasPhone, String reason,LeaveType leavetype) {
		
// 		this.applicationDate = applicationDate;
// 		this.leaveStartDate = leaveStartDate;
// 		this.leaveEndDate = leaveEndDate;
// 		this.workDelegate = workDelegate;
// 		this.overseasPhone = overseasPhone;
// 		this.reason = reason;
// 		this.leaveType = leavetype;
		
// 		this.status=LeaveTypeStatus.APPLIED;
// 		this.active=true;
// 	}
	
// 	//update with form input, rather than static method
// 	public void updateUsingForm(LeaveAppForm leaveAppForm, LocalDateTime inputTime,LeaveType lt) {
		
// 		EmployeeServiceImpl eService=new EmployeeServiceImpl();
// 		Employee desiredEmployee=eService.findEmployeeByName(leaveAppForm.getWorkDelegate());
		
// 		//return null for invalid application
// 	    if(!ldt.isValid(leaveAppForm.getLeaveStartDate().atTime(0,0,0), leaveAppForm.getLeaveEndDate().atTime(0,0,0))) {
// 	    	return;
// 	    }
// 			this.applicationDate = inputTime;
// 			this.leaveStartDate = leaveAppForm.getLeaveStartDate().atTime(0,0,0);
// 			this.leaveEndDate = leaveAppForm.getLeaveEndDate().atTime(0,0,0);
// 			this.workDelegate = desiredEmployee;
// 			this.overseasPhone = leaveAppForm.getOverseasPhone();
// 			this.reason = leaveAppForm.getReason();
// 			this.leaveType = lt;
			
// 			this.status=LeaveTypeStatus.UPDATED;	
// 	}
	
	
	
// 	//---Override ToString---

// 	@Override
// 	public String toString() {
	  
// 	  return "Leave Application [id = " + id + ", name = " + reason + "]";
// 	}
	
	
	
// 	//remap leave start and end?
// 	//autogen getters/setters
// }
