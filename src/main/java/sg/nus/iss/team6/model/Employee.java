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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(emailAdress, employee.emailAdress) &&
                Objects.equals(roleId, employee.roleId) &&
                Objects.equals(teamId, employee.teamId) &&
                Objects.equals(overtimeBalance, employee.overtimeBalance) &&
                Objects.equals(role, employee.role) &&
                Objects.equals(active, employee.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, teamId, overtimeBalance, role, active);
    }
	
}