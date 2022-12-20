package sg.nus.iss.team6.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import sg.nus.iss.team6.util.dayEnum;

@Data
@Entity
public class Team {

	@Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private String description;

    @OneToMany
    @JoinColumn(name="team_id") 
    private List<Employee> EmployeesList;
	
    @Column(name = "active_status", nullable = false)
	private boolean active;
    
	public Team() {}
	
    public Team(String name, String description) {
		this.name = name;
		this.description = description;
		this.active=true;
	}
    
    public void setTeamMembers(List<Employee> members) {
    	this.EmployeesList=members;    	
    }


}