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
import sg.nus.iss.team6.util.LeaveTypeStatus;
import sg.nus.iss.team6.util.ldt;
import sg.nus.iss.team6.validator.AnnualLeaveValidator;
import sg.nus.iss.team6.validator.LeaveAppFormValidator;

@Controller
@RequestMapping(value = "/leave")
public class LeaveApplicationController {

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
	private AnnualLeaveValidator annualLeaveValidator;

	@InitBinder("leaveAppForm")
	private void initCourseBinder(WebDataBinder binder) {
		binder.addValidators(leaveAppFormValidator);
		binder.addValidators(annualLeaveValidator);
	}

	/**
	 * CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/history")
	public String LeaveApplicationHistory(HttpSession session, Model model) {

		// TODO combine with UserSession
		// hardcode EmployeeId first
		int currentUserId = 1;
		Employee currentUser = eService.findEmployee(currentUserId);
		System.out.println(currentUser.getName());

		List<LeaveApplication> leaveApplications = currentUser.getLeaveApplications();
		List<LeaveApplication> laAnnual= new ArrayList<>();
		List<LeaveApplication> laMedical= new ArrayList<>();
		List<LeaveApplication> laCompensation= new ArrayList<>();
		for(LeaveApplication la:leaveApplications) {
			if(!la.getActive())
				continue;
			if (la.getLeaveType().getTypeName()=="Annual") 
				laAnnual.add(la);
			if (la.getLeaveType().getTypeName()=="Medical") 
				laMedical.add(la);
			if (la.getLeaveType().getTypeName()=="Compensation") 
				laCompensation.add(la);
		}
		
		model.addAttribute("laAnnual", laAnnual);
		model.addAttribute("laMedical", laMedical);
		model.addAttribute("laCompensation", laCompensation);


		return "leaveApplicationHistory";
	}
	
	
	
	
	
	

	
	
	
	

//
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
