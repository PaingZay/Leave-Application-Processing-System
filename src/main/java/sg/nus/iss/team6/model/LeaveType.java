package sg.nus.iss.team6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import sg.nus.iss.team6.util.LeaveTypeStatus;

@Data
@Entity
public class LeaveType {

	public LeaveType() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id
	@Column(name="leaveTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "type_name", unique = true, nullable = false)
	private String typeName;
	
	@Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveTypeStatus status;
	
	@OneToMany(mappedBy = "leaveType")
	private List<LeaveEntitlement> leaveEntitles;
}
