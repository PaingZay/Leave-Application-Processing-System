package sg.nus.iss.laps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.laps.model.Application;
import sg.nus.iss.laps.model.ApplicationStatus;
import sg.nus.iss.laps.service.ApplicationService;
import sg.nus.iss.laps.service.UserService;

@Controller
@RequestMapping(value = "/leaveapplication")
public class ApplicationController {
	
	@Autowired
	  private ApplicationService applicationService;

	@RequestMapping(value = "/manage")
		//public String employeeCourseHistory(HttpSession session, Model model)
	  	public String employeeCourseHistory(Model model) {
//	    UserSession usession = (UserSession) session.getAttribute("usession");
	    
//	    System.out.println(usession.getEmployee());
	    
//	    List<Course> employeeCourses = courseService.findCoursesByEID(usession.getEmployee().getEmployeeId());
//	    model.addAttribute("chistory", employeeCourses);
	    
	    //My code
	    List<Application> applications = applicationService.findAllApplication();
	    model.addAttribute("chistory", applications);
	    
	    //return "test";
	    return "leaveapplication";
	  }
	
	@GetMapping("/viewdetail/{id}")
	  public String viewDetails(@PathVariable Integer id, Model model) {
	    Application appdetails = applicationService.findApplication(id);
	    model.addAttribute("appdetails", appdetails);
	    
	    return "leaveapplicationdetails";
	  }
	
	  @PostMapping("/viewdetail/{id}")
	  public String editCourse(@ModelAttribute @Valid Application application, BindingResult result, @PathVariable Integer id,
	      HttpSession session){
	    //I think some validation here are discarded for right now
	    //UserSession usession = (UserSession) session.getAttribute("usession");
	    //application.setEmployeeId(usession.getEmployee().getId());//Need to negotiate
	    //application.setEmployee(usession.getEmployee());
	    application.setStatus(ApplicationStatus.APPROVED);
		
		//Just testing and successfully got data
//		System.out.println(application.getEmployee().getId());
//		System.out.println(application.getEmployee().getName());
//	    System.out.println(application.getComment());
//	    System.out.println(application.getApplicationDate());
//	    System.out.println(application.getLeaveStartDate());
//	    System.out.println(application.getLeaveEndDate());
	    
	    applicationService.changeApplication(application);
//	    
	    return "redirect:/leaveapplication/manage";
	  }
	  
//	@RequestMapping(value="/viewdetails/{id}", method=RequestMethod.POST)
//	public String edit(@ModelAttribute @Valid Application application, BindingResult result, @PathVariable Integer id, HttpSession session, @RequestParam(value="action", required=true) String action)
//	{
//
//	    if (action.equals("approve")) {
//	    	application.setStatus(ApplicationStatus.APPROVED);
//		    applicationService.changeApplication(application);   
//	    }
//
//	    if (action.equals("reject")) {
//	    	application.setStatus(ApplicationStatus.REJECTED);
//		    applicationService.changeApplication(application);
//	    }
//	    return "redirect:/leaveapplication/manage";
//	}
	
//	@RequestMapping(value="/viewdetails/{id}", method=RequestMethod.POST,params="action=approve")
//	  public String ApproveApplication(@ModelAttribute @Valid Application application, BindingResult result, @PathVariable Integer id, HttpSession session){
//		  	application.setStatus(ApplicationStatus.APPROVED);
//		    applicationService.changeApplication(application);
//		    return "redirect:/leaveapplication/manage";
//		  
//	  }
//	
//	  @RequestMapping(value = "/viewdetails/{id}", method = RequestMethod.POST, params="action=reject")
//	  public String RejectApplication(@ModelAttribute @Valid Application application, BindingResult result, @PathVariable Integer id, HttpSession session){
//		  	application.setStatus(ApplicationStatus.REJECTED);
//		    applicationService.changeApplication(application);
//		    return "redirect:/leaveapplication/manage";
//		  
//	  }
}