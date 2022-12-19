package sg.nus.iss.team6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="employee")
	private List<LeaveApplication> applications;
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "Name is required")
	private String name;

	private String phone;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email Format")
	private String emailAddress;
	
	private int roleId;
	
	private int teamId;
	
	private double overtimeBalance;

	private boolean active = true;

	@Column(name = "managerid")
	private String managerId;

	public Employee() { }
	  
	public Employee(String username, String password, String name, String phone, String emailAddress, int roleId, int teamId, double overtimeBalance, boolean active){
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.roleId = roleId;
	    this.teamId = teamId;
	    this.overtimeBalance = overtimeBalance;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<LeaveApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<LeaveApplication> applications) {
		this.applications = applications;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public double getOvertimeBalance() {
		return overtimeBalance;
	}

	public void setOvertimeBalance(double overtimeBalance) {
		this.overtimeBalance = overtimeBalance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
