package sg.nus.iss.team6.controller;

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
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.service.LeaveApplicationService;

@Controller
@RequestMapping(value = "/leaveapplication")
public class ApplicationController {
	
	@Autowired
	  private LeaveApplicationService applicationService;

	@RequestMapping(value = "/manage")
	  	public String employeeCourseHistory(Model model) {
//	    UserSession usession = (UserSession) session.getAttribute("usession");
	    List<LeaveApplication> applications = applicationService.findAllApplication();
	    model.addAttribute("chistory", applications);
	    return "leaveapplication";
	  }
	
	@GetMapping("/viewdetail/{id}")
	  public String viewDetails(@PathVariable Integer id, Model model) {
	    LeaveApplication appdetails = applicationService.findApplication(id);
	    model.addAttribute("appdetails", appdetails);
	    
	    return "leaveapplicationdetails";
	  }
	
	  @PostMapping("/viewdetail/{id}")
	  public String editCourse(@ModelAttribute @Valid LeaveApplication application, BindingResult result, @PathVariable Integer id,
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
	    return "redirect:/leaveapplication/manage";
	  }
}