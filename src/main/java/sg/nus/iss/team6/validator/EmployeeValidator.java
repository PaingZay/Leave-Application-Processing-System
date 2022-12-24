package sg.nus.iss.team6.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sg.nus.iss.team6.model.Employee;

@Component
public class EmployeeValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Employee.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    System.out.println(target);
    
  }
}
