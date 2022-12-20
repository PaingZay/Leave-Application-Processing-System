package sg.nus.iss.team6.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sg.nus.iss.team6.model.PublicHoliday;

@Component
public class PHValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PublicHoliday.class.isAssignableFrom(clazz);

  }

  @Override
  public void validate(Object target, Errors errors) {
    System.out.println(target);
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", "Holiday name is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phDay", "error.phDay", "Day is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phMonth", "error.phMonth", "Month is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phLength", "error.phLength", "Period is required.");

  }

}
