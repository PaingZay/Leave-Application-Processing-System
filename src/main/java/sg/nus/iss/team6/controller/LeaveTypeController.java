package sg.nus.iss.team6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.service.LeaveTypeService;

@Data
@Controller
@RequestMapping(value="/leave")
public class LeaveTypeController {

	public LeaveTypeController() {
		// TODO Auto-generated constructor stub
	}
	
	private LeaveTypeService leaveTypeService;
	
	@GetMapping(value = "/add")
    public String addPerson(Model model) {
        LeaveType leaveType = new LeaveType();
        model.addAttribute("leaveTypey", leaveType);

        return "addLeaveType";
    }
	
	@PostMapping(value="/add")
	public String addNewLeaveType(@ModelAttribute(value="leaveType") LeaveType lt) {
		
		leaveTypeService.save(lt);
		return "Redirect:leave/list";
		
	}

	public void removeLeaveType() {
		
	}
	
	
}
