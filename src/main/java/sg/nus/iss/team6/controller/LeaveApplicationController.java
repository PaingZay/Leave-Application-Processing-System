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
import sg.nus.iss.team6.validator.AnnualLeaveValidator;
import sg.nus.iss.team6.validator.LeaveAppFormValidator;
//import sg.nus.iss.team6.validator.LeaveAppNotFound;

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

	@Autowired
	private LeaveTypeService ltService;

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
	  public String leaveAppHistory(HttpSession session, Model model) {
	    //Uncomment  combine with login /authentication if not for testing or
		//String username = (String) session.getAttribute("username");
	    //Test purpose
		final String username="Jonjon1";
	    System.out.println("username:"+username);
	    
	    Employee currentUser=eService.findEmployeeByUserName(username);
	    int id=eService.findEmployeeByUserName(username).getId();
	    model.addAttribute("employeeId",id);
	    
	    
	    List<LeaveApplication> applicationList=currentUser.getLeaveApplications();
	    List<LeaveApplication> toRemove= new ArrayList<>();
	    
	    for (LeaveApplication la :applicationList) {
	    	if (la.getActive()==false) {
	    		toRemove.add(la);
	    	}
	    }
	    applicationList.removeAll(toRemove);
	    
	    System.out.println("employeeID:" +id);
	    model.addAttribute("leaveHistory", applicationList);
	      
	    return "leave-my-history";
	  }


	  @GetMapping("/edit/{id}")
	  public String editLeavePage(@PathVariable Integer id, Model model) {
		  
	    LeaveApplication leaveApp = laService.findLeaveApplication(id);
	    List<LeaveType> leaveTlist= ltService.findAll();
	    model.addAttribute("leaveTlist", leaveTlist);
	    model.addAttribute("leaveApp", leaveApp);
	    
	    return "leave-edit";
	  }
	  

	  @PostMapping("/edit/{id}")
	  public String editLeaveApp(@ModelAttribute @Valid LeaveApplication leaveApplication, BindingResult result, @PathVariable Integer id,
	      HttpSession session) {
	    if (result.hasErrors())
	      return "leave-edit";
	    
	    
	    leaveApplication.setStatus(ApplicationStatus.UPDATED);
	    leaveApplication.setId(id);
	    
	    laService.changeLeaveApplication(leaveApplication);
	    
	    return "redirect:/leave/history";
	  }

	  
	  
	  
	  
	  @RequestMapping(value = "/withdraw/{id}")
	  public String deleteLeaveApp(@PathVariable Integer id) {
	
	    LeaveApplication leaveApplication = laService.findLeaveApplication(id);
	    
	    leaveApplication.setStatus(ApplicationStatus.CANCELLED);
	    laService.changeLeaveApplication(leaveApplication);

	    String message = "Leave ID " + leaveApplication.getId() + " was successfully withdrawn.";
	    System.out.println(message);
	    
	    return "redirect:/leave/history";
	  }

}
