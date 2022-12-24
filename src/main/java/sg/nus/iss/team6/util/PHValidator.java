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
    
    ValidationUtils.rejectIfEmpty(errors, "name", "error.ph.name.empty");
	/*
	 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phDay",
	 * "error.ph.day.empty"); ValidationUtils.rejectIfEmptyOrWhitespace(errors,
	 * "phMonth", "error.ph.month.empty");
	 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phLength",
	 * "error.ph.length.empty");
	 */

  }

}
