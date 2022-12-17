package sg.nus.iss.team6.model;

import java.util.List;

public class UserSession {
  private Employee employee;
  private List<Employee> subordinates;
  
  public UserSession() {}
  
  public UserSession(Employee employee, List<Employee> subordinates) {
    this.employee = employee;
    this.subordinates = subordinates;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public List<Employee> getSubordinates() {
    return subordinates;
  }

  public void setSubordinates(List<Employee> subordinates) {
    this.subordinates = subordinates;
  }
  
  
}
