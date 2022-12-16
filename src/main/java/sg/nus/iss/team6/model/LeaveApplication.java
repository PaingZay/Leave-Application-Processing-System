package sg.nus.iss.team6.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class LeaveApplication {
	
    @ManyToOne
    @JoinColumn(name="leaveType_Id")
	private LeaveType leaveType;

}
