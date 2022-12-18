package sg.nus.iss.team6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="employee")
	private List<LeaveApplication> applications;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String emailAddress;
	
	private int roleId;
	
	private int teamId;
	
	private double overtimeBalance;

	@Column(name = "managerid")
	private String managerId;

	public Employee() { }
	  
	public Employee(String username, String password, String name, String emailAddress, int roleId, int teamId, double overtimeBalance){
		this.username = username;
		this.password = password;
		this.name = name;
		this.emailAddress = emailAddress;
		this.roleId = roleId;
	    this.teamId = teamId;
	    this.overtimeBalance = overtimeBalance;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
