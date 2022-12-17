package sg.nus.iss.team6.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import sg.nus.iss.team6.controller.service.PublicHolidayService;
import sg.nus.iss.team6.controller.service.RoleService;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.util.LeaveTypeStatus;
import sg.nus.iss.team6.util.ldt;




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
//  @Autowired
//  private LeaveApplicationValidator laValidator;

//  @InitBinder("course")
//  private void initCourseBinder(WebDataBinder binder) {
//    binder.addValidators(laValidator);
//  }

  /**
   * CRUD OPERATIONS
   * 
   * @return
   */
  
  ////get
//  @RequestMapping(value = "course/history")
//  List<Course> employeeCourses = courseService.findCoursesByEID(usession.getEmployee().getEmployeeId());
//  model.addAttribute("chistory", employeeCourses);
  

  @GetMapping("/apply/annual")
  public String newLeaveApplication(Model model) {
    model.addAttribute("leaveAppForm", new LeaveAppForm());
    
    return "leaveApplicationAnnual";
  }

  @PostMapping("/apply/annual")
  public String createNewLeaveApplication(@ModelAttribute @Valid LeaveAppForm leaveAppForm, BindingResult result, HttpSession session) {
	  
    if (result.hasErrors()) {
      return "leaveApplication-new";
    }
   

    //TODO combine with UserSession
    //hardcode EmployeeId first	
    int currentUserId=1;
    //get the current user object
    Employee currentUser=eService.findEmployee(currentUserId);
    
    //debug purpose
//    String currentName=currentUser.getName();
//    System.out.println(currentName);
    
    //get the leaveTypes available to the current user
    //access the maxEntitlement and minGranularity from this list
    	//SQL service doesn't work- debug next time, for now use entities
    //List<LeaveType> availableLT= rService.findLeaveTypesByRole(currentUser.getRole().getId());
    List<LeaveType> availableLT=currentUser.getRole().getLeaveTypes();

    //debug purpose
//    for (LeaveType lt : availableLT)
//    	System.out.println("Item number: "+lt.getDescription()+" "+lt.getMaxEntitlement()+" "+lt.getMinGranularity());

    //get current user's appliedLeaves
    List<LeaveApplication> appliedLeaves=currentUser.getLeaveApplications();
    
    //get list of all public holidays
    List<PublicHoliday> publicHolidays=phService.findAllPublicHolidays();
    
    //get current timestamp
    LocalDateTime now = LocalDateTime.now();
    //get year of leave
    Integer leaveAppStartYear = leaveAppForm.getLeaveStartDate().getYear();
    //Integer leaveAppEndYear = leaveAppForm.getLeaveEndDate().getYear();
    
    //create a list for conflicting public holidays
    //check if it is empty, not null?
    List<PublicHoliday> overlappingPh=new ArrayList<>();
    
    
  	//find employees from the same team
    List<Employee> teamEmployees =  eService.findEmployeesByTeam(currentUserId);
    //remove self from the team list
    teamEmployees.remove(eService.findEmployee(currentUserId));
    //look for desired employee
    Employee desiredEmployee=eService.findEmployeeByName(leaveAppForm.getWorkDelegate());
    
    
    
    if(!teamEmployees.contains(desiredEmployee)) {
    	String errorMsg="The delegated employee does not exist in your team. Please check their exact name.";
    	//TODO: return error message to the page
    	return "leaveApplication-new";
    }
    
    if(!ldt.isValid(leaveAppForm.getLeaveStartDate().atTime(0,0,0), leaveAppForm.getLeaveEndDate().atTime(0,0,0))) {
    	String errorMsg="The end date comes before the start date.";
    	//TODO: return error message to the page
    	return "leaveApplication-new";
    }
    
    if(!ldt.isValid(now, leaveAppForm.getLeaveStartDate().atTime(0,0,0))) {
    	String errorMsg="You cannot apply for leave retroactively.";
    	//TODO: return error message to the page
    	return "leaveApplication-new";
    }
    
    //check for overlaps with previous leave
    for (LeaveApplication prevLeave: appliedLeaves) {
    	
    	if(ldt.isOverlap(leaveAppForm.getLeaveStartDate().atTime(0,0,0),leaveAppForm.getLeaveEndDate().atTime(0,0,0),prevLeave.getLeaveStartDate(),prevLeave.getLeaveEndDate())) {
        	String errorMsg="This application conflicts with one of your pre-existing leave applications.";
        	//TODO: return error message to the page
        	return "leaveApplication-new";
        }
    }
    
    //check for overlaps with PH
    for (PublicHoliday phol: publicHolidays) {
    	
    	if(ldt.isOverlap(leaveAppForm.getLeaveStartDate().atTime(0,0,0),leaveAppForm.getLeaveEndDate().atTime(0,0,0),phol.getLDTByYear(leaveAppStartYear),phol.getLDTEndByYear(leaveAppStartYear))) {
    		overlappingPh.add(phol);
        }
    }
    
    
    
    
    
    //convert leaveAppForm to leaveApplication object
    
//    leaveAppForm.leaveStartDate = ;
//    leaveAppForm.leaveEndDate = leaveEndDate;
//	this.workDelegate = workDelegate;
//	this.overseasPhone = overseasPhone;
//	this.reason = reason;
//    
    
    
//    //input missing formfields
//    leaveApplication.setApplicationDate(now);
//    //TODO: some logic to derive Work Dissemination/Distribution
//    leaveApplication.setStatus(LeaveTypeStatus.APPLIED);
//    leaveApplication.setActive(true);
    
    
    //persist in same order as SpringCaApplication.java
//    laService.createLeaveApplication(leaveApplication);
//    placeHolderLogin.addLeaveApplication(leaveApplication);
    
    String message = "New leave application was successfully created.";
    System.out.println(message);
    
    return "redirect:/leave/apply/annual";
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
