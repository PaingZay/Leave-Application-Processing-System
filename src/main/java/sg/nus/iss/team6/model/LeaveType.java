package sg.nus.iss.team6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class LeaveType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@Length(max = 30, message = "Too long")
	@NotBlank(message = "Please enter leave type")
	private String typeName;
		
	@Column(nullable = false)
	@Min(value = 0, message = "Too short")
	@Max(value = 60, message = "Too long")
	@NotNull(message = "Enter a number")
	private Double maxEntitlement;
	
	@Column(nullable = false)
	@NotNull(message = "Please enter granularity")
	private Double minGranularity;
	
	@Column(nullable = true)
	@Length(max = 200, message = "Too long")
	private String description;
	
	@Column(nullable = false)
	private boolean active;

	@OneToMany(mappedBy="leaveType")
	private List<LeaveApplication> leaveApplications;

	@ManyToOne
    private Role role;
    
    //Getters/Setters------------------------------------------
    

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

    
    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Double getMaxEntitlement() {
		return maxEntitlement;
	}


	public void setMaxEntitlement(Double maxEntitlement) {
		this.maxEntitlement = maxEntitlement;
	}


	public Double getMinGranularity() {
		return minGranularity;
	}


	public void setMinGranularity(Double minGranularity) {
		this.minGranularity = minGranularity;
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

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

    //Constructors---------------------------------------------


	public LeaveType() {}
	

	//set both maxEntitlement and minGranularity to Double so they are nullable
	public LeaveType(String typeName, Double maxEntitlement, Double minGranularity, String description, boolean active, Role role) {
		this.typeName = typeName;
		this.maxEntitlement = maxEntitlement;
		this.minGranularity = minGranularity;
		this.description = description;
		this.active = active;
		this.role = role;
	}
}
