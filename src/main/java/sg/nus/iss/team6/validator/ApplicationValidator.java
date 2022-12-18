package sg.nus.iss.team6.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.team6.model.LeaveApplication;

@Component
public class ApplicationValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return LeaveApplication.class.isAssignableFrom(clazz);

  }

  @Override
  public void validate(Object target, Errors errors) {
    LeaveApplication application = (LeaveApplication) target;  
    if ((application.getLeaveStartDate() != null && application.getLeaveEndDate() != null) &&
          (application.getLeaveStartDate().compareTo(application.getLeaveEndDate()) > 0)) {
      errors.reject("toDate", "End date should be greater than start date.");
      errors.rejectValue("toDate", "error.dates", "to date must be > from date");  
    }
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseName", "error.courseName", "Course name is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "error.fromDate", "From Date is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "error.toDate", "To Date is required.");
  }

}
