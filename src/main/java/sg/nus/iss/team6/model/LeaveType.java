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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "type_name", unique = true, nullable = false)
	private String name;
	
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
    
	/*
	 * @OneToMany(mappedBy="leaveType_Id") private List<LeaveApplication>
	 * listLeaveApp;
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeaveType other = (LeaveType) obj;
		return Id == other.Id && active == other.active && Objects.equals(descrition, other.descrition)
				&& maxEntitlement == other.maxEntitlement
				&& Double.doubleToLongBits(minGranularity) == Double.doubleToLongBits(other.minGranularity)
				&& Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, active, descrition, maxEntitlement, minGranularity, name);
	}

	public LeaveType(int id, String name, int maxEntitlement, double minGranularity, String descrition, boolean active,
			Role role) {
		super();
		Id = id;
		this.name = name;
		this.maxEntitlement = maxEntitlement;
		this.minGranularity = minGranularity;
		this.descrition = descrition;
		this.active = active;
		this.role = role;
	}


	
}
