package sg.nus.iss.team6.controller.exception;


public class PHNotFound extends Exception {
  private static final long serialVersionUID = 1L;
  
  public PHNotFound() {}
  
  public PHNotFound(String msg) {
    super(msg);
  }
}
