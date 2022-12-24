package sg.nus.iss.team6.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team6.model.Role;


@Component
public class LeaveTypeValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Role.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    System.out.println(target);
    
    ValidationUtils.rejectIfEmpty(errors, "Id", "error.leavetype.id.empty");
    ValidationUtils.rejectIfEmpty(errors, "name", "error.leavetype.name.empty");
    ValidationUtils.rejectIfEmpty(errors, "description", "error.leavetype.description.empty");
  }
  
}