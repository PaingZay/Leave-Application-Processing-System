package sg.nus.iss.team6.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveAppForm;
import sg.nus.iss.team6.service.EmployeeService;
import sg.nus.iss.team6.service.LeaveAppFormService;
import sg.nus.iss.team6.service.LeaveApplicationService;
import sg.nus.iss.team6.service.LeaveTypeService;
import sg.nus.iss.team6.service.PublicHolidayService;
import sg.nus.iss.team6.service.RoleService;
import sg.nus.iss.team6.validator.AnnualLeaveValidator;
import sg.nus.iss.team6.validator.LeaveAppFormValidator;
//import sg.nus.iss.team6.util.EmailAPI;

@Controller
@RequestMapping(value = "/leave")
public class LeaveApplicationController {

	@Autowired
	private LeaveApplicationService applicationService;

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

	@Autowired
	private EmployeeService employeeService;

	@InitBinder("leaveAppForm")
	private void initCourseBinder(WebDataBinder binder) {
		binder.addValidators(leaveAppFormValidator);
	}

	
	
	
	@RequestMapping(value = "/history")
	public String leaveAppHistory(HttpSession session, Model model) {

		// TODO:Uncomment combine with login /authentication if not for testing or
		// String username = (String) session.getAttribute("username");
		String username = (String) session.getAttribute("username");
		Employee currentUser = eService.findUser(username);
		int id = currentUser.getId();
		model.addAttribute("employeeId",id);
		

	    List<LeaveApplication> applicationList=new ArrayList<>();
	    applicationList.addAll(currentUser.getLeaveApplications());
	    List<LeaveApplication> toRemove= new ArrayList<>();
	    
	    for (LeaveApplication la :applicationList) {
	    	if (la.getActive()==false) {
	    		toRemove.add(la);
	    	}
//	    	else if(la.getStatus()==ApplicationStatus.APPROVED || la.getStatus()==ApplicationStatus.REJECTED) {
//	    		toRemove.add(la);
//	    	}
	    }
	    applicationList.removeAll(toRemove);
	    toRemove= new ArrayList<>();
	    
	    //------------------
//	    List<LeaveApplication> appApproved=new ArrayList<>();
//	    List<LeaveApplication> appRejected=new ArrayList<>();
//	    
	    List<LeaveApplication> appAnnual=new ArrayList<>();
	    List<LeaveApplication> appMedical=new ArrayList<>();
	    List<LeaveApplication> appCompensation=new ArrayList<>();

	    
//	    //Approved
//	    for (LeaveApplication la:applicationList) {
//	    	if(la.getStatus()==ApplicationStatus.APPROVED) {
//		    	appApproved.add(la);
//		    	toRemove.add(la);
//	    	}
//	    }
//	    applicationList.removeAll(toRemove);
//	    toRemove= new ArrayList<>();
//	    
//	    //Rejected
//	    for (LeaveApplication la:applicationList) {
//	    	if(la.getStatus()==ApplicationStatus.REJECTED) {
//		    	appRejected.add(la);
//		    	toRemove.add(la);
//	    	}
//	    }
	    applicationList.removeAll(toRemove);
	    toRemove= new ArrayList<>();
	    
	    //Annual
	    for (LeaveApplication la:applicationList) {
	    	if(la.getLeaveType().getTypeName()=="Annual") {
		    	appAnnual.add(la);
		    	toRemove.add(la);
	    	}
	    }
	    applicationList.removeAll(toRemove);
	    toRemove= new ArrayList<>();
	    
	    //Medical
	    for (LeaveApplication la:applicationList) {
	    	if(la.getLeaveType().getTypeName()=="Medical") {
		    	appMedical.add(la);
		    	toRemove.add(la);
	    	}
	    }
	    applicationList.removeAll(toRemove);
	    toRemove= new ArrayList<>();
	    
	    //Compensation
	    for (LeaveApplication la:applicationList) {
	    	if(la.getLeaveType().getTypeName()=="Compensation") {
		    	appCompensation.add(la);
		    	toRemove.add(la);
	    	}
	    }
	    applicationList.removeAll(toRemove);
	    toRemove= new ArrayList<>();
	    


	    //------------------
	    
	    
	    System.out.println("employeeID:" +id);
	    model.addAttribute("leaveHistory", applicationList);
	    model.addAttribute("annual", appAnnual);
	    model.addAttribute("medical", appMedical);
	    model.addAttribute("compensation", appCompensation);
//	    model.addAttribute("approved", appApproved);
//	    model.addAttribute("rejected", appRejected);
//	      
	    return "leave-my-history";
	  }
	

	@GetMapping("/edit/{id}")
	public String editLeavePage(@PathVariable Integer id, Model model, HttpSession session) {

		// TODO:Uncomment combine with login /authentication if not for testing or
		// String username = (String) session.getAttribute("username");
		String username = (String) session.getAttribute("username");
		Employee currentUser = eService.findUser(username);
		int currentUserId = currentUser.getId();

		LeaveApplication leaveApp = applicationService.findLeaveApplication(id);
		List<LeaveType> leaveTlist = ltService.findAll();
		LeaveAppForm leaveAppForm = new LeaveAppForm();

		leaveAppForm.setLeaveStartDate(leaveApp.getLeaveStartDate().toLocalDate());
		leaveAppForm.setLeaveEndDate(leaveApp.getLeaveEndDate().toLocalDate());
		leaveAppForm.setLeaveStartTime(leaveApp.getLeaveStartDate().getHour());
		leaveAppForm.setLeaveEndTime(leaveApp.getLeaveEndDate().getHour());

		if (leaveApp.getWorkDelegate() != null) {
			leaveAppForm.setWorkDelegate(leaveApp.getWorkDelegate().getName());
		} else {
			leaveAppForm.setWorkDelegate("");
		}
		if (leaveApp.getOverseasPhone() != null) {
			leaveAppForm.setOverseasPhone(leaveApp.getOverseasPhone());
		} else {
			leaveAppForm.setOverseasPhone("");
		}

		if (leaveApp.getReason() != null) {
			leaveAppForm.setReason(leaveApp.getReason());
		} else {
			leaveAppForm.setReason("");
		}

		leaveAppForm.setLeaveTypeName(leaveApp.getLeaveType().getTypeName());
		// leaveAppForm.setApplicantId(currentUserId);
		leaveAppForm.setLeaveId(id);

		model.addAttribute("leaveTlist", leaveTlist);
		model.addAttribute("leaveApp", leaveApp);
		model.addAttribute("leaveAppForm", leaveAppForm);

		return "leave-edit";
	}

	@PostMapping("/edit/{id}")
	public String editLeaveApp(@ModelAttribute LeaveAppForm leaveAppForm, BindingResult result,
			@PathVariable Integer id, HttpSession session) {

		String username = (String) session.getAttribute("username");

		Employee currentUser = eService.findEmployeeByUserName(username);
		int currentUserId = eService.findEmployeeByUserName(username).getId();
		
		LeaveApplication leaveApp = applicationService.findLeaveApplication(leaveAppForm.getLeaveId());
		Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());

		leaveApp.setWorkDelegate(desiredEmployee);
		leaveApp.setOverseasPhone(leaveAppForm.getOverseasPhone());
		leaveApp.setReason(leaveAppForm.getReason());

		applicationService.changeLeaveApplication(leaveApp);

		eService.changeEmployee(currentUser);

		return "redirect:/leave/history";
	}

	@RequestMapping(value = "/withdraw/{id}")
	public String deleteLeaveApp(@PathVariable Integer id) {

		LeaveApplication leaveApplication = applicationService.findLeaveApplication(id);

		leaveApplication.setStatus(ApplicationStatus.CANCELLED);
		applicationService.changeLeaveApplication(leaveApplication);

		String message = "Leave ID " + leaveApplication.getId() + " was successfully withdrawn.";
		System.out.println(message);

		return "redirect:/leave/history";
	}

	
	@RequestMapping(value = "/manage")
	public String employeeCourseHistory(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		Employee e = employeeService.findUser(username);
		// List<LeaveApplication> applications =
		// applicationService.findAllApplication();
		List<LeaveApplication> prelimApplications = applicationService.findApplicationsByTeamId(e.getTeamId());
		
		List<LeaveApplication> applications = prelimApplications
			.stream()
			.sorted((la1,la2)->
				la1.getEmployee().getUsername()
				.compareTo(
				la2.getEmployee().getUsername()))
			.collect(Collectors.toList())
			;
			
		model.addAttribute("chistory", applications);
		return "leaveapplication";
	}

	
	
	@RequestMapping(value = "/subordinateleavehistory")
	public String subordinateLeaveHistory(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		Employee e = employeeService.findUser(username);
		List<LeaveApplication> applications = applicationService.findLeaveHistoryByTeamId(e.getTeamId());
		List<LeaveApplication> prelimNewApplications = new ArrayList<>();
		for (LeaveApplication la : applications) {
			if (la.getStatus().equals(ApplicationStatus.REJECTED)
					|| la.getStatus().equals(ApplicationStatus.APPROVED)) {
				prelimNewApplications.add(la);
			}
		}
		for (LeaveApplication la : prelimNewApplications) {
			System.out.println(la.getStatus());
		}
		List<LeaveApplication> newApplications =prelimNewApplications
				.stream()
				.sorted((la1,la2)->
					la1.getEmployee().getUsername()
					.compareTo(
					la2.getEmployee().getUsername()))
				.collect(Collectors.toList())
				;
				

		model.addAttribute("applicationList", newApplications);
		return "subordinateLeaveHistory";
	}
	
	

	@RequestMapping(value = "/viewdetail/{id}", method = RequestMethod.GET)
	public String viewDetails(@PathVariable Integer id, Model model) {
		LeaveApplication appdetails = applicationService.findApplication(id);
		model.addAttribute("appdetails", appdetails);
		return "leaveapplicationdetails";
	}

	@RequestMapping(value = "/viewdetail/{id}", method = RequestMethod.POST, params = "approve")
	public String Approve(@ModelAttribute @Valid LeaveApplication application, BindingResult result,
			@PathVariable Integer id, HttpSession session, Model model) {
		applicationService.updateApplicationById(application.getId(), application.getComment(),
				ApplicationStatus.APPROVED, LocalDateTime.now());

		String url = ServletUriComponentsBuilder.fromCurrentRequest()
				.replacePath("/login")
				.build()
				.toUriString();
		
		//TODO: integrate
//		EmailAPI.SendEmail(application.getEmployee().getEmailAddress(),
//				EmailAPI.GetApproveEmailTitle(application.getEmployee().getName()),
//				EmailAPI.GetApproveEmailBody(application.getEmployee().getName(), url));

		return "redirect:/leave/manage";
	}

	@RequestMapping(value = "/viewdetail/{id}", method = RequestMethod.POST, params = "reject")
	public String Reject(@ModelAttribute @Valid LeaveApplication application, BindingResult result,
			@PathVariable Integer id, HttpSession session, Model model) {

		LeaveApplication application1 = applicationService.findApplication(application.getId());

		// application1 is current application

		String comment = application.getComment();// Is current application's comment empty

		if (comment == "") {
			model.addAttribute("EmptyComment", "Requires a comment");
			model.addAttribute("appdetails", application1);
			return "leaveapplicationdetails";
		}
		application1.setComment(application.getComment());
		applicationService.updateApplicationById(application1.getId(), application1.getComment(),
				ApplicationStatus.REJECTED, LocalDateTime.now());

		String url = ServletUriComponentsBuilder.fromCurrentRequest()
				.replacePath("/login")
				.build()
				.toUriString();

		//TODO: integrate
//		EmailAPI.SendEmail(application1.getEmployee().getEmailAddress(),
//				EmailAPI.GetRejectEmailTitle(application1.getEmployee().getName()),
//				EmailAPI.GetRejectEmailBody(application1.getEmployee().getName(), url));
		
		
		return "redirect:/leave/manage";
	}

	public LeaveApplicationService getApplicationService() {
		return applicationService;
	}

	public void setApplicationService(LeaveApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public EmployeeService geteService() {
		return eService;
	}

	public void seteService(EmployeeService eService) {
		this.eService = eService;
	}

	public RoleService getrService() {
		return rService;
	}

	public void setrService(RoleService rService) {
		this.rService = rService;
	}

	public PublicHolidayService getPhService() {
		return phService;
	}

	public void setPhService(PublicHolidayService phService) {
		this.phService = phService;
	}

	public LeaveTypeService getLtService() {
		return ltService;
	}

	public void setLtService(LeaveTypeService ltService) {
		this.ltService = ltService;
	}

	public LeaveAppFormValidator getLeaveAppFormValidator() {
		return leaveAppFormValidator;
	}

	public void setLeaveAppFormValidator(LeaveAppFormValidator leaveAppFormValidator) {
		this.leaveAppFormValidator = leaveAppFormValidator;
	}

	public AnnualLeaveValidator getAnnualLeaveValidator() {
		return annualLeaveValidator;
	}

	public void setAnnualLeaveValidator(AnnualLeaveValidator annualLeaveValidator) {
		this.annualLeaveValidator = annualLeaveValidator;
	}
}
