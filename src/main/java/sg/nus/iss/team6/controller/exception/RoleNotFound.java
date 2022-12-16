package sg.nus.iss.team6.controller.exception;


public class RoleNotFound extends Exception {
  private static final long serialVersionUID = 1L;
  
  public RoleNotFound() {}
  
  public RoleNotFound(String msg) {
    super(msg);
  }
}
