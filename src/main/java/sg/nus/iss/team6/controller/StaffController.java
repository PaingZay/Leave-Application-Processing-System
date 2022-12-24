package sg.nus.iss.team6.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.service.EmployeeService;
import org.springframework.ui.Model;

@Controller
@Component
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/menu") 
	  public String menu(Model model, HttpSession session) {
		
		String name = (String) session.getAttribute("username");
		Employee e = employeeService.findUser(name);
		model.addAttribute("name", e.getName());
	    return "StaffView";
	  }
}


