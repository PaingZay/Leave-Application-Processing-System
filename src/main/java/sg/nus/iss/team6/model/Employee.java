package sg.nus.iss.team6.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee")
public class Employee {
	@Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@NotBlank(message = "Username is required")
	@Column(name = "user_name", unique = true, nullable = false)
    private String username;

	@NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;
    
	@NotBlank(message = "Name is required")
    @Column(name = "full_name", nullable = false)
    private String name;

	@Column(name="phone")
	private String phone;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email Format")
	private String emailAddress;
	
//	@ManyToOne
//    @JoinColumn(name="employee_Id")
//    private Team team;
	@Column(name = "team_Id", nullable = false)
	private int teamId;
	
	@Column(name = "ot_balance", nullable = false)
	private double overtimeBalance;

	@ManyToOne
	private Role role;
	
	@OneToMany(mappedBy="employee")
	private List<LeaveApplication> leaveApplications;
	
	//unidirectional (I added)
	@OneToMany 
	@JoinColumn(name="employeeid") 
	private List<OvertimeChit> overtimeChits;

	
	
	//-----------------------------------
	
	public List<OvertimeChit> getOvertimeChits() {
		return overtimeChits;
	}

	public void setOvertimeChits(List<OvertimeChit> overtimeChits) {
		this.overtimeChits = overtimeChits;
	}

	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	private boolean active = true;

	public Employee() { }
	  
	public Employee(String username, String password, String name, String phone, String emailAddress, Role role, int teamId, double overtimeBalance, boolean active){
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.role = role;
	    this.teamId = teamId;
	    this.overtimeBalance = overtimeBalance;
		this.active = active;
	}
	
	public Employee(String username, String password, String name, String phone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;

		this.overtimeBalance=0;
		//remember to include for logical deletes!
		this.active=true;
	}

	public Employee(String username, String password, String name, String phone, Role role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.role= role;
		
		this.overtimeBalance=0;
		//remember to include for logical deletes!
		this.active=true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<LeaveApplication> getLeaveApplications() {
		return leaveApplications;
	}

	public void setLeaveApplications(List<LeaveApplication> applications) {
		this.leaveApplications = applications;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	//METHODS-------------------------------------------------------------------
    
	public void addLeaveApplication(LeaveApplication la) {
		if (leaveApplications==null) {
			List<LeaveApplication> toAdd=new ArrayList<>();
			toAdd.add(la);
			this.leaveApplications=toAdd;
		}
		else {
			leaveApplications.add(la);
		}
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(emailAddress, employee.emailAddress) &&
                Objects.equals(overtimeBalance, employee.overtimeBalance) &&
                Objects.equals(role, employee.role) &&
                Objects.equals(active, employee.active);
    }

	// Cyrus Method
	// @Override
	// public boolean equals(Object obj) {
	// 	if (this == obj)
	// 		return true;
	// 	if (obj == null)
	// 		return false;
	// 	if (getClass() != obj.getClass())
	// 		return false;
	// 	Employee other = (Employee) obj;
	// 	return active == other.active && Objects.equals(emailAddress, other.emailAddress) && id == other.id
	// 			&& Objects.equals(name, other.name)
	// 			&& Double.doubleToLongBits(overtimeBalance) == Double.doubleToLongBits(other.overtimeBalance)
	// 			&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
	// 			&& Objects.equals(role, other.role) && teamId == other.teamId
	// 			&& Objects.equals(username, other.username);
	// }



	@Override
    public int hashCode() {
        return Objects.hash(id, username, overtimeBalance, role, active);
    }
}