package sg.nus.iss.team6.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Role")
public class Role {
	
	@Column(name="role_Id")
	@Id
	private int Id;
	
    @Column(name = "hie_value", nullable = false)
	private int hierarchyValue;
	
    @Column(name = "role_name", unique = true, nullable = false)
	private String name;
	
    @Column(name = "role_description", nullable = true)
	private String description;
	
    @Column(name = "active_status", nullable = false)
	private boolean active;
    
    @OneToMany(mappedBy="role")
    private List<Employee> listEmployee;
    
    @OneToMany(mappedBy="role")
    private List<LeaveType> listLeaveType;

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
				&& hierarchyValue == other.hierarchyValue && Id == other.Id
				&& Objects.equals(listEmployee, other.listEmployee)
				&& Objects.equals(listLeaveType, other.listLeaveType) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description, hierarchyValue, Id, listEmployee, listLeaveType, name);
	}

	public Role(int id, int hierarchyValue, String name, String description, boolean active) {
		super();
		Id = id;
		this.hierarchyValue = hierarchyValue;
		this.name = name;
		this.description = description;
		this.active = active;
	}

	public Role() {
	}
    
	
}
