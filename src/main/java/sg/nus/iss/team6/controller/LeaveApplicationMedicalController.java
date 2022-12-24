package sg.nus.iss.team6.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.nus.iss.team6.service.EmployeeService;
import sg.nus.iss.team6.service.LeaveApplicationService;
import sg.nus.iss.team6.service.LeaveTypeService;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.service.PublicHolidayService;
import sg.nus.iss.team6.service.RoleService;
import sg.nus.iss.team6.validator.LeaveAppFormValidator;
import sg.nus.iss.team6.validator.MedicalLeaveValidator;

@Controller
@RequestMapping(value = "/leave")
public class LeaveApplicationMedicalController {

	@Autowired
	private LeaveApplicationService laService;

	@Autowired
	private EmployeeService eService;

	@Autowired
	private RoleService rService;

	@Autowired
	private PublicHolidayService phService;

	@Autowired
	private LeaveTypeService ltService;

	@Autowired
	private LeaveAppFormValidator leaveAppFormValidator;

	@Autowired
	private MedicalLeaveValidator medicalLeaveValidator;

	@InitBinder("leaveAppForm")
	private void initCourseBinder(WebDataBinder binder) {
		binder.addValidators(leaveAppFormValidator);
		binder.addValidators(medicalLeaveValidator);
	}

	/**
	 * CRUD OPERATIONS
	 * 
	 * @return
	 */

	


	@GetMapping("/apply/medical")
	public String newMedicalLeaveApplication(Model model) {

		model.addAttribute("leaveAppForm", new LeaveAppForm());

		return "leaveApplicationMedical";
	}
	
	@PostMapping("/apply/medical")
	public String createNewMedicalLeaveApplication(@ModelAttribute @Valid LeaveAppForm leaveAppForm, BindingResult result,
			HttpSession session) {

		// check for errors
		if (result.hasErrors()) {
			return "leaveApplicationMedical";
		}

		String username = (String) session.getAttribute("username");
		Employee e = eService.findUser(username);
		int currentUserId = e.getId();

		Employee currentUser = eService.findEmployee(currentUserId);
		LeaveType leaveType = ltService.findLeaveTypeByNameAndRole("Medical", currentUser.getRole());
		Double maxEntitlement = leaveType.getMaxEntitlement();
		Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());

		// get year of leave
		Integer leaveAppStartYear = leaveAppForm.getLeaveStartDate().getYear();

		LocalDateTime leaveStart = leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0);
		LocalDateTime leaveEnd = leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0);

		LeaveApplication myLA = leaveAppForm.convertToLA(currentUser,LocalDateTime.now(), leaveType, desiredEmployee, leaveStart,
				leaveEnd);
		laService.createLeaveApplication(myLA);

		currentUser.addLeaveApplication(myLA);
		eService.changeEmployee(currentUser);

		String message = "New leave application was successfully created.";
		System.out.println(message);

		return "redirect:/leave/apply/medical";
	}
	
	

}
