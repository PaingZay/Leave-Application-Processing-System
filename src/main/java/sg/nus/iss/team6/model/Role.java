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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
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
				&& Objects.equals(listEmployee, other.listEmployee) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description, hierarchyValue, id, listEmployee, name);
	}

    
}
