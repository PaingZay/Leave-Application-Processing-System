package sg.nus.iss.team6.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LeaveType")
public class LeaveType {

	public LeaveType() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@Column(name="leaveTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "type_name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "role_Id", unique = true, nullable = false )
	private int roleId;
	
	@Column(name = "maxEntitle", nullable = false)
    private int maxEntitlement;
	
	@Column(name="Min_granularity", nullable=false)
	private double minGranularity;
	
	@Column(name="Descrition", nullable=true)
	private String descrition;
	
	@Column(name="Active", nullable=false)
	private boolean active;

    @ManyToOne
    @JoinColumn(name="role_Id")
    private Role role;
    
    @OneToMany(mappedBy="leaveType_Id")
    private List<LeaveApplication> listLeaveApp;

	public LeaveType(int id, String name, int roleId, int maxEntitlement, double minGranularity, String descrition,
			boolean active) {
		super();
		Id = id;
		this.name = name;
		this.roleId = roleId;
		this.maxEntitlement = maxEntitlement;
		this.minGranularity = minGranularity;
		this.descrition = descrition;
		this.active = active;
	}

    
    
}
