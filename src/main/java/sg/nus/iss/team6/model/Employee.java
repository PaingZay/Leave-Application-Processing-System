package sg.nus.iss.team6.model;

import lombok.Data;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.util.ldt;

import javax.persistence.*;

import javax.persistence.Column;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "full_name", nullable = false)
    private String name;

    //added by Jason--------------------------------------------------------
    @Column(name = "phone_number", nullable = false)
    private String phone;
    //added by Jason--------------------------------------------------------

    @Column(name = "emailAdress")
    private String emailAdress;

    //duplicate team_id deleted by Jason--------------------------------------
    
    @Column(name = "ot_balance", nullable = false)
    private double overtimeBalance;
    
    //deleted from other side- use unidirectional
    @ManyToOne
    @JoinColumn(name="role")
    private Role role;
    
    //added by Jason--------------------------------------------------------
	@OneToMany
	@JoinColumn(name="employee_id") 
	private List<LeaveApplication> leaveApplications;
	
	@OneToMany
	@JoinColumn(name="employeeid") 
	private List<OvertimeChit> overtimeChits;
	
    //added by Jason--------------------------------------------------------
    
    
    //changed by Jason--------------------------------------------------------
    @Column(name = "active_status", nullable = false)
	private boolean active;
    
    
    
    //---Getters/Setters-------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<LeaveApplication> getLeaveApplications() {
		return leaveApplications;
	}

	public void setLeaveApplications(List<LeaveApplication> leaveApplications) {
		this.leaveApplications = leaveApplications;
	}
	
	public List<OvertimeChit> getOvertimeChits() {
		return overtimeChits;
	}

	public void setOvertimeChits(List<OvertimeChit> overtimeChits) {
		this.overtimeChits = overtimeChits;
	}
	
    //METHODS-------------------------------------------------------------------
    
	public List<LeaveApplication> getLeaveApplicationsForPeriodAndType(Integer yearNum, LeaveType leaveType) {
		
		List<LeaveApplication> toReturn = getLeaveApplications();
		List<LeaveApplication> leavesToRemove = new ArrayList<>();
		
		LocalDateTime yearStart =LocalDateTime.of(yearNum,1,1, 0,0,0);
		LocalDateTime yearEnd =LocalDateTime.of(yearNum,12,31, 23,59,59);
		
		for(LeaveApplication la:toReturn) {
			if(la.getLeaveType()!=leaveType) {
				leavesToRemove.add(la);
			}
			else if (!ldt.isOverlap(yearStart, yearEnd, la.getLeaveStartDate(), la.getLeaveEndDate())){
				leavesToRemove.add(la);
			}
		}
		toReturn.removeAll(leavesToRemove);
		return toReturn;
	}


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




	public Employee() {}
    


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
                Objects.equals(emailAdress, employee.emailAdress) &&
                Objects.equals(overtimeBalance, employee.overtimeBalance) &&
                Objects.equals(role, employee.role) &&
                Objects.equals(active, employee.active);
    }




	@Override
    public int hashCode() {
        return Objects.hash(id, username, overtimeBalance, role, active);
    }


	
}