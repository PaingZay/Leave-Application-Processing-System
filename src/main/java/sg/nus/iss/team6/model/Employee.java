package sg.nus.iss.team6.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "full_name", nullable = false)
    private String name;
    
    @Column(name="phone")
    private String phone;

    @Column(name = "emailAdress")
    private String emailAdress;


    @Column(name = "role_Id", nullable = false)
    private int roleId;

    @Column(name = "team_Id", nullable = false)
    private int teamId;

    @Column(name = "ot_balance", nullable = false)
    private double overtimeBalance;
    
    @ManyToOne
    @JoinColumn(name="role_Id")
    private Role role;
    
    @Column
    private boolean active;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return active == other.active && Objects.equals(emailAdress, other.emailAdress) && id == other.id
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(overtimeBalance) == Double.doubleToLongBits(other.overtimeBalance)
				&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
				&& Objects.equals(role, other.role) && roleId == other.roleId && teamId == other.teamId
				&& Objects.equals(username, other.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, emailAdress, id, name, overtimeBalance, password, phone, role, roleId, teamId,
				username);
	}
	
}