package sg.nus.iss.team6.controller;

import java.time.LocalDateTime;
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
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.util.LeaveTypeStatus;




@Controller
@RequestMapping(value = "/employee")
public class LeaveApplicationController {
	
  @Autowired
  private LeaveApplicationService laService;
  
  @Autowired
  private EmployeeService eService;

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
  

  @GetMapping("/leave/apply")
  public String newLeaveApplication(Model model) {
    model.addAttribute("leaveAppForm", new LeaveAppForm());
    
    return "leaveApplication-new";
  }

  @PostMapping("/leave/apply")
  public String createNewLeaveApplication(@ModelAttribute @Valid LeaveAppForm leaveAppform, BindingResult result, HttpSession session) {
	  
    if (result.hasErrors()) {
      return "leaveApplication-new";
    }
    
    System.out.println(leaveAppform.toString());
    

    //TODO combine with UserSession
    //hardcode EmployeeId first
    int currentUserId=1;
    Employee placeHolderLogin=eService.findEmployee(currentUserId);
    
    //get current timestamp
    LocalDateTime now = LocalDateTime.now();
    
  	//find employees from the same team
    List<Employee> employees =  eService.findEmployeesByTeam(currentUserId);
    //remove self from the list
    employees.remove(eService.findEmployee(currentUserId));
    
    
    
//    leaveAppform.leaveStartDate = ;
//    leaveAppform.leaveEndDate = leaveEndDate;
//	this.workDelegate = workDelegate;
//	this.overseasPhone = overseasPhone;
//	this.reason = reason;
    
    
    
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
    
    return "redirect:/employee/leave/apply";
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
