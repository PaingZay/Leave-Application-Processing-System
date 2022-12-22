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
	}

	/**
	 * CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/history")
	  public String leaveAppHistory(HttpSession session, Model model) {
		
	    //TODO:Uncomment  combine with login /authentication if not for testing or
		//String username = (String) session.getAttribute("username");
		final String username="Jonjon1";
	    
	    Employee currentUser=eService.findEmployeeByUserName(username);
	    int id=eService.findEmployeeByUserName(username).getId();
	    model.addAttribute("employeeId",id);
	    
	    
	    List<LeaveApplication> applicationList=new ArrayList<>();
	    applicationList.addAll(currentUser.getLeaveApplications());
	    List<LeaveApplication> toRemove= new ArrayList<>();
	    
	    for (LeaveApplication la :applicationList) {
	    	if (la.getActive()==false) {
	    		toRemove.add(la);
	    	}
	    	else if(la.getStatus()==ApplicationStatus.APPROVED || la.getStatus()==ApplicationStatus.REJECTED) {
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
		  
	    //TODO:Uncomment  combine with login /authentication if not for testing or
		//String username = (String) session.getAttribute("username");
		final String username="Jonjon1";
		
	    Employee currentUser=eService.findEmployeeByUserName(username);
	    int currentUserId=eService.findEmployeeByUserName(username).getId();
	  
	    LeaveApplication leaveApp = laService.findLeaveApplication(id);
	    List<LeaveType> leaveTlist= ltService.findAll();
	    LeaveAppForm leaveAppForm = new LeaveAppForm();
	    
	    leaveAppForm.setLeaveStartDate(leaveApp.getLeaveStartDate().toLocalDate());
	    leaveAppForm.setLeaveEndDate(leaveApp.getLeaveEndDate().toLocalDate());
	    leaveAppForm.setLeaveStartTime(leaveApp.getLeaveStartDate().getHour());
	    leaveAppForm.setLeaveEndTime(leaveApp.getLeaveEndDate().getHour());
	    
	    if(leaveApp.getWorkDelegate()!=null) {
	    	leaveAppForm.setWorkDelegate(leaveApp.getWorkDelegate().getName());
	    }
	    else {
	    	leaveAppForm.setWorkDelegate("");
	    }
	    if(leaveApp.getOverseasPhone()!=null) {
	    	leaveAppForm.setOverseasPhone(leaveApp.getOverseasPhone());
	    }
	    else {
	    	leaveAppForm.setOverseasPhone("");
	    }
	    
	    if(leaveApp.getReason()!=null) {
	    	 leaveAppForm.setReason(leaveApp.getReason());
	    }
	    else {
	    	leaveAppForm.setReason("");
	    }
		
	    leaveAppForm.setLeaveTypeName(leaveApp.getLeaveType().getTypeName());
	    leaveAppForm.setApplicantId(leaveApp.getId());
	    leaveAppForm.setLeaveId(id);
	    
	    model.addAttribute("leaveTlist", leaveTlist);
	    model.addAttribute("leaveApp", leaveApp);
	    model.addAttribute("leaveAppForm", leaveAppForm);
	   
	    return "leave-edit";
	  }
	  

	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  @PostMapping("/edit/{id}")
	  public String editLeaveApp(@ModelAttribute LeaveAppForm leaveAppForm, BindingResult result, @PathVariable Integer id, HttpSession session) {
		  

		    Employee currentUser = eService.findEmployee(leaveAppForm.getApplicantId());
		    LeaveApplication leaveApp = laService.findLeaveApplication(leaveAppForm.getLeaveId());
		    Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());
		    
		    leaveApp.setWorkDelegate(desiredEmployee);
		    leaveApp.setOverseasPhone(leaveAppForm.getOverseasPhone());
		    leaveApp.setReason(leaveAppForm.getReason());
		    
		    laService.changeLeaveApplication(leaveApp);
		    
		    eService.changeEmployee(currentUser);
		    
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
