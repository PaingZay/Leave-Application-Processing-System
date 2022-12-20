package sg.nus.iss.team6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Role")
public class Role {

	@Column(name = "role_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Column(name = "hie_value", nullable = false)
	private int hierarchyValue;

	@Column(name = "role_name", unique = true, nullable = false)
	private String name;

	@Column(name = "role_description", nullable = true)
	private String description;
	
	//@OneToMany (cascade = CascadeType.ALL)
	//@JoinColumn(name="role_id") 
	@ManyToMany	
	private List<LeaveType> leaveTypes;

	// changed by Jason--------------------------------------------------------
	@Column(name = "active_status", nullable = false)
	private boolean active;

	// changed by Jason--------------------------------------------------------
//	@OneToMany
//	@JoinColumn(name="role_id") 
//	private List<Employee> listEmployee;
	
	//--Getters/Setters---------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getHierarchyValue() {
		return hierarchyValue;
	}

	public void setHierarchyValue(int hierarchyValue) {
		this.hierarchyValue = hierarchyValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LeaveType> getLeaveTypes() {
		return leaveTypes;
	}
	
	public void setLeaveTypes(List<LeaveType> lt) {
		this.leaveTypes=lt;
	}
//	public void addRole(Employee employee) {
//		// toadd validation
//		listEmployee.add(employee);
//	}
	
	//--Constructors-----
	


	public Role() {}



	public Role(int hValue, String name, String description) {
		this.hierarchyValue = hValue;
		this.name = name;
		this.description = description;
		
//		listEmployee = new ArrayList<Employee>();
		this.active = true;
	}



	// ------------------------------------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return active == other.active && Objects.equals(description, other.description)
				&& hierarchyValue == other.hierarchyValue && id == other.id
				&& Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description, hierarchyValue, id, name);
	}

}
