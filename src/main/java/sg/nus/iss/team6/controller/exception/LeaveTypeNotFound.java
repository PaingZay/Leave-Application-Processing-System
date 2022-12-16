package sg.nus.iss.team6.controller.exception;


public class LeaveTypeNotFound extends Exception {
  private static final long serialVersionUID = 1L;
  
  public LeaveTypeNotFound() {}
  
  public LeaveTypeNotFound(String msg) {
    super(msg);
  }
}
