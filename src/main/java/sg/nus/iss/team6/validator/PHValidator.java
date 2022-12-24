package sg.nus.iss.team6.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team6.model.LeaveAppForm;
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
    
	PublicHoliday ph = (PublicHoliday) target;

	/*
	 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phDay",
	 * "error.ph.day.empty"); ValidationUtils.rejectIfEmptyOrWhitespace(errors,
	 * "phMonth", "error.ph.month.empty");
	 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phLength",
	 * "error.ph.length.empty");
	 */
	if(ph.getPhDay() <1 ||ph.getPhDay() > 31) {
		errors.reject("phDay", "Enter a valid day!");
		errors.rejectValue("phDay", "error.dates", "Enter a valid day!");
	}
	if(ph.getPhMonth() <1 ||ph.getPhMonth() > 12) {
		errors.reject("phMonth", "Enter a valid month!");
		errors.rejectValue("phMonth", "error.dates", "Enter a valid month!");
	}

    ValidationUtils.rejectIfEmpty(errors, "name", "error.ph.name.empty");
  }

}
