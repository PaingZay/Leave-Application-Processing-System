package sg.nus.iss.team6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LeaveEntitlement")
public class LeaveEntitlement {

	public LeaveEntitlement() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@Column(name="leave_entitleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private int balanceLeave;
	
	@ManyToOne
	@JoinColumn(name="leaveTypeId")
	private LeaveType leaveType;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;
}
