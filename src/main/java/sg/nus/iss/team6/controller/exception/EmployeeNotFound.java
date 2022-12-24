package sg.nus.iss.team6.controller.exception;

public class EmployeeNotFound extends Exception{
  private static final long serialVersionUID = 1L;
  
  public EmployeeNotFound() {}
  
  public EmployeeNotFound(String msg) {
    super(msg);
  }
}
