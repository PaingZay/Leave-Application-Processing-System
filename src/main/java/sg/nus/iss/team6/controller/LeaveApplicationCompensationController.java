package sg.nus.iss.team6.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.team6.controller.service.EmployeeService;
import sg.nus.iss.team6.controller.service.LeaveApplicationService;
import sg.nus.iss.team6.controller.service.LeaveTypeService;
import sg.nus.iss.team6.controller.service.PublicHolidayService;
import sg.nus.iss.team6.controller.service.RoleService;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.util.ApplicationStatus;
import sg.nus.iss.team6.util.ldt;
import sg.nus.iss.team6.validator.CompensationLeaveValidator;
import sg.nus.iss.team6.validator.LeaveAppFormValidator;


@Controller
@RequestMapping(value = "/leave")
public class LeaveApplicationCompensationController {

	@Autowired
	private LeaveApplicationService laService;

	@Autowired
	private EmployeeService eService;

	@Autowired
	private RoleService rService;

	@Autowired
	private PublicHolidayService phService;

	// TODO: refactor to autowired
	private LeaveTypeService ltService = new LeaveTypeService();

	@Autowired
	private LeaveAppFormValidator leaveAppFormValidator;

	@Autowired
	private CompensationLeaveValidator compensationLeaveValidator;

	@InitBinder("leaveAppForm")
	private void initCourseBinder(WebDataBinder binder) {
		binder.addValidators(leaveAppFormValidator);
		binder.addValidators(compensationLeaveValidator);
	}

	/**
	 * CRUD OPERATIONS
	 * 
	 * @return
	 */

	


	@GetMapping("/apply/compensation")
	public String newCompensationLeaveApplication(Model model) {

		model.addAttribute("leaveAppForm", new LeaveAppForm());

		return "leaveApplicationCompensation";
	}
	
	@PostMapping("/apply/compensation")
	public String createNewCompensationLeaveApplication(@ModelAttribute @Valid LeaveAppForm leaveAppForm, BindingResult result,
			HttpSession session) {

		// check for errors
		if (result.hasErrors()) {
			return "leaveApplicationCompensation";
		}

		// TODO combine with UserSession
		// hardcode EmployeeId first
		int currentUserId = leaveAppForm.getApplicantId();

		Employee currentUser = eService.findEmployee(currentUserId);
		LeaveType leaveType = ltService.findLeaveTypeByNameAndRole("Compensation", currentUser.getRole());
		
		Double minGranularity = leaveType.getMinGranularity();
		
		Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());

		// get year of leave
		Integer leaveAppStartYear = leaveAppForm.getLeaveStartDate().getYear();

		LocalDateTime leaveStart = leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0);
		LocalDateTime leaveEnd = leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0);

		LeaveApplication myLA = leaveAppForm.convertToLA(LocalDateTime.now(), leaveType, desiredEmployee,
				leaveEnd);
		laService.createLeaveApplication(myLA);

		currentUser.addLeaveApplication(myLA);
		eService.changeEmployee(currentUser);

		String message = "New compensation leave application was successfully created.";
		System.out.println(message);

		return "redirect:/leave/apply/compensation";
	}
	
	

//  @GetMapping("/course/edit/{id}")
//  public String editCoursePage(@PathVariable Integer id, Model model) {
//    Course course = courseService.findCourse(id);
//    model.addAttribute("course", course);
//    
//    return "course-edit";
//  }
//
//  @PostMapping("/course/edit/{id}")
//  public String editCourse(@ModelAttribute @Valid Course course, BindingResult result, @PathVariable Integer id,
//      HttpSession session) throws CourseNotFound {
//    if (result.hasErrors())
//      return "course-edit";
//    
//    System.out.println("DATES****" + course.getFromDate() + course.getToDate());
//    
//    UserSession usession = (UserSession) session.getAttribute("usession");
//    course.setEmployeeId(usession.getEmployee().getEmployeeId());
//    course.setStatus(CourseEventEnum.UPDATED);
//    
//    courseService.changeCourse(course);
//    
//    return "redirect:/staff/course/history";
//  }
//
//  @RequestMapping(value = "/course/withdraw/{id}")
//  public String deleteCourse(@PathVariable Integer id) throws CourseNotFound {
//    Course course = courseService.findCourse(id);
//    
//    course.setStatus(CourseEventEnum.WITHDRAWN);
//    courseService.changeCourse(course);
//
//    String message = "Course " + course.getCourseId() + " was successfully withdrawn.";
//    System.out.println(message);
//    
//    return "redirect:/staff/course/history";
//  }

}
