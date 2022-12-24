package sg.nus.iss.team6.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.TypeMismatchException;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Entity
@Table(name="Role")
public class Role {
	
	@Id
	private int Id;
	
    @Column(nullable = false)
	@Min(value = 1, message = "Too short")
	@Max(value = 30, message = "Too long")
	@NotNull(message = "Enter a number")
	private int hierarchyValue;
	
    @Column(unique = true, nullable = false)
	@Length(max = 30, message = "Too long")
	@NotBlank(message = "Please enter role name")
	private String name;
	
    @Column(nullable = true)
	@Length(max = 200, message = "Too long")
	private String description;
	
    @Column(nullable = false)
	private boolean active;
    
    @OneToMany(mappedBy="role")
    private List<Employee> listEmployee;
    
    @OneToMany(mappedBy="role")
    private List<LeaveType> leaveTypes;

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
				&& Objects.equals(leaveTypes, other.leaveTypes) && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description, hierarchyValue, Id, listEmployee, leaveTypes, name);
	}

	public Role(int Id, int hierarchyValue, String name, String description, boolean active) {
		super();
		this.Id = Id;
		this.hierarchyValue = hierarchyValue;
		this.name = name;
		this.description = description;
		this.active = active;
	}

	public Role() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<LeaveType> getLeaveTypes() {
		return leaveTypes;
	}

	public void setLeaveTypes(List<LeaveType> listLeaveType) {
		this.leaveTypes = listLeaveType;
	}
	
}





// package sg.nus.iss.team6.model;

// import java.util.List;
// import java.util.Objects;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

// import lombok.Data;

// @Data
// @Entity
// @Table(name="Role")
// public class Role {
	
// 	@Column(name="role_Id")
// 	@Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int Id;
	
//     @Column(name = "hie_value", nullable = false)
// 	private int hierarchyValue;
	
//     @Column(name = "role_name", unique = true, nullable = false)
// 	private String name;
	
//     @Column(name = "role_description", nullable = true)
// 	private String description;
	
//     @Column(name = "active_status", nullable = false)
// 	private boolean active;
    
//   @OneToMany(mappedBy="role")
//   private List<Employee> listEmployee;
    
//   @OneToMany(mappedBy="role")
//   private List<LeaveType> listLeaveType;
    

// 	@Override
// 	public boolean equals(Object obj) {
// 		if (this == obj)
// 			return true;
// 		if (obj == null)
// 			return false;
// 		if (getClass() != obj.getClass())
// 			return false;
// 		Role other = (Role) obj;
// 		return active == other.active && Objects.equals(description, other.description)
// 				&& hierarchyValue == other.hierarchyValue && Id == other.Id
// 				&& Objects.equals(listEmployee, other.listEmployee)
// 				&& Objects.equals(listLeaveType, other.listLeaveType) && Objects.equals(name, other.name);
// 	}

// 	@Override
// 	public int hashCode() {
// 		return Objects.hash(active, description, hierarchyValue, Id, listEmployee, listLeaveType, name);
// 	}

// 	// public Role(int id, int hierarchyValue, String name, String description, boolean active) {
// 	// 	super();
// 	// 	Id = id;
// 	// 	this.hierarchyValue = hierarchyValue;
// 	// 	this.name = name;
// 	// 	this.description = description;
// 	// 	this.active = active;
// 	// }

//   public Role(int hierarchyValue, String name, String description, boolean active) {
// 		this.hierarchyValue = hierarchyValue;
// 		this.name = name;
// 		this.description = description;
// 		this.active = active;
// 	}
//   public Role(int hierarchyValue) {
// 		this.hierarchyValue = hierarchyValue;
// 	}
//   public Role()
//   {}
// }












// // package sg.nus.iss.team6.model;

// // import java.io.Serializable;
// // import java.util.Objects;

// // import javax.persistence.Column;
// // import javax.persistence.Entity;
// // import javax.persistence.Id;
// // import javax.persistence.Table;

// // /**
// //  * Course class
// //  *
// //  * @version $Revision: 1.0
// //  * @author Suria
// //  * 
// //  */

// // @Entity
// // @Table(name = "role")
// // public class Role implements Serializable {
// //   private static final long serialVersionUID = 6529685098267757690L;
  
// //   @Id
// //   @Column(name = "roleid")
// //   private int id;
  
// //   @Column(name = "name")
// //   private String name;
  
// //   @Column(name = "description")
// //   private String description;
  
// //   @Column(name = "hierarchyValue")
// //   private int hierarchyValue;
  
// //   public Role() {}
  
// //   public Role(int roleId) {
// //     this.id = roleId;
// //   }
  
// //   public Role(int id, String name, String description) {
// //     this.id = id;
// //     this.name = name;
// //     this.description = description;
// //   }

  
  
// //   public int getId() {
// // 	return id;
// // }

// // public void setId(int id) {
// // 	this.id = id;
// // }

// // public int getRoleId() {
// //     return id;
// //   }

// //   public void setRoleId(int id) {
// //     this.id = id;
// //   }

// //   public String getName() {
// //     return name;
// //   }

// //   public void setName(String name) {
// //     this.name = name;
// //   }

// //   public String getDescription() {
// //     return description;
// //   }

// //   public void setDescription(String description) {
// //     this.description = description;
// //   }

// //   @Override
// //   public int hashCode() {
// //     return Objects.hash(id);
// //   }

// //   @Override
// //   public boolean equals(Object obj) {
// //     if (this == obj)
// //       return true;
// //     if (obj == null)
// //       return false;
// //     if (getClass() != obj.getClass())
// //       return false;
// //     Role other = (Role) obj;
// //     return Objects.equals(id, other.id);
// //   }

// // public int getHierarchyValue() {
// // 	return hierarchyValue;
// // }

// // public void setHierarchyValue(int hierarchyValue) {
// // 	this.hierarchyValue = hierarchyValue;
// // }
// // }
