package sg.nus.iss.team6.controller;

import java.util.List;

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

import sg.nus.iss.team6.controller.exception.LeaveTypeNotFound;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.service.LeaveTypeServiceImpl;


@Controller
@RequestMapping(value="/admin/leavetype")
public class LeaveTypeController {

	public LeaveTypeController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private LeaveTypeServiceImpl rService;
	
	/**
	 * LEAVE TYPE CRUD OPERATIONS
	 * 
	 * @return
	 */
	@GetMapping("/list")
  public String leaveTypeListPage(Model model) {
    List<LeaveType> leaveTypeList = rService.findAllLeaveType();
    model.addAttribute("leaveTypeList", leaveTypeList);
    
    return "leavetype-list";
  }
	
	@GetMapping("/create")
	public String newLeaveTypePage(Model model) {
	  model.addAttribute("leaveType", new LeaveType());
		
		return "leavetype-new";
	}

	@PostMapping("/create")
	public String createNewLeaveType(@ModelAttribute @Valid LeaveType leaveType, BindingResult result) {
		if (result.hasErrors()) {
			return "leavetype-new";
		}

		String message = "New LeaveType " + leaveType.getId() + " was successfully created.";
		System.out.println(message);
		rService.createLeaveType(leaveType);
		
		return "redirect:/admin/leavetype/list";
	}
	
	@GetMapping("/edit/{id}")
	public String editLeaveTypePage(@PathVariable Integer id, Model model) {
		LeaveType leaveType = rService.findLeaveType(id);
		model.addAttribute("leaveType",leaveType);
		
		return "leavetype-edit";
	}

	@PostMapping("/edit/{id}")
	public String editLeaveType(@ModelAttribute @Valid LeaveType leaveType, BindingResult result, 
			@PathVariable String id) throws LeaveTypeNotFound {
		if (result.hasErrors()) {
			return "leavetype-edit";
		}

		String message = "LeaveType was successfully updated.";
		System.out.println(message);
		rService.changeLeaveType(leaveType);
		
		return "redirect:/admin/leavetype/list";
	}
	
}
