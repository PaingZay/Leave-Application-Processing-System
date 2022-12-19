package sg.nus.iss.team6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class LeaveType {
	
	@Id
	@Column(name="leaveTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//changed by Jason, was Id--------------------------------------------------------
	
	//does not need to be unique (name = "type_name", unique = true, nullable = false)
	@Column(name = "type_name", nullable = false)
	private String typeName;
		
	//added by Jason--------------------------------------------------------
	////roleid?
	@Column(name = "max_Entitlement")
	private Double maxEntitlement;
	
	@Column(name = "min_Granularity")
	private Double minGranularity;
	
	@Column(name = "description")
	private String description;

	
    @Column(name = "active_status", nullable = false)
	private boolean active;
    
	//added by Jason--------------------------------------------------------
	
    
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

    //Constructors---------------------------------------------


	public LeaveType() {}
	
	
	


	//set both maxEntitlement and minGranularity to Double so they are nullable
	public LeaveType(String typeName, Double maxEntitlement, Double minGranularity, String description) {
		this.typeName = typeName;
		this.maxEntitlement = maxEntitlement;
		this.minGranularity = minGranularity;
		this.description = description;
		
		this.active=true;
	}

//redundant constructor
//	public LeaveType(String typeName, Double minGranularity, String description) {
//		
//		this.typeName = typeName;
//		this.minGranularity = minGranularity;
//		this.description = description;
//		
//		this.active=true;
//	}
	

}
