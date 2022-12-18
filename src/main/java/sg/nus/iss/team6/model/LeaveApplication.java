package sg.nus.iss.team6.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.validation.constraints.FutureOrPresent;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

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
	
//	@NotNull(message = "Application date must be provided")
//	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate applicationDate;
	
//	@NotNull(message = "Start date must be provided")
//	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveStartDate;
	
//	@NotNull(message = "End date must be provided")
//	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveEndDate;
	
	private String leaveType;
	
	private String comment;
	
	
	@Column(name = "status", columnDefinition = "ENUM('APPLIED','UPDATED','DELETED','APPROVED','REJECTED')")
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	
	// No args and argument constructor
	public LeaveApplication(){}
//	public Application(@NotEmpty Employee employee, @NotEmpty LocalDate applicationDate, @FutureOrPresent LocalDate leaveStartDate, @FutureOrPresent LocalDate leaveEndDate, String leaveType, ApplicationStatus status, String comment)
//	{
//		this.employee = employee;
//		this.applicationDate = applicationDate;
//		this.leaveStartDate = leaveStartDate;
//		this.leaveEndDate = leaveEndDate;
//		this.leaveType = leaveType;
//		this.status = status;
//		this.comment = comment;
//	}
	public LeaveApplication(Employee employee,LocalDate applicationDate,LocalDate leaveStartDate,LocalDate leaveEndDate, String leaveType, ApplicationStatus status, String comment)
	{
		this.employee = employee;
		this.applicationDate = applicationDate;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.leaveType = leaveType;
		this.status = status;
		this.comment = comment;
	}
	
	
	//Following are auto-generated getters and setters
	
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
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
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
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
}
