package sg.nus.iss.team6.controller;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.service.EmployeeService;
import sg.nus.iss.team6.validator.EmployeeValidator;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeValidator employeeValidator;
	


	@InitBinder("employee")
	private void initEmployeeBinder(WebDataBinder binder) {
	  binder.addValidators(employeeValidator);
	}	  
	
	@GetMapping("/menu") 
	  public String menu(Model model, HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		Employee e = employeeService.findUser(username);
		model.addAttribute("name", e.getName());
		
		List<Employee> employeeList = employeeService.findAllEmployees();
		model.addAttribute("employeeList", employeeList);
		
	    return "AdminView";
	  }

	
	
		
////  Register   //////////////////////////////////////////////////////////////	
	
	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public String RegistrationPage(Model model) 
	  {
		model.addAttribute("employee", new Employee());
	    
	    return "register";
	  }

	  @RequestMapping(value = "/register", method = RequestMethod.POST)
	  public String createNewEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) 
	  {
		if (result.hasErrors()) 
		{
		    return "register";
		}
		
		List<String> usernameList = employeeService.findAllUsernames();
		String username = employee.getUsername();
		int roleId = employee.getRole().getId();
		int teamId = employee.getTeamId();
		
		if (usernameList.contains(username)) 
		{
			model.addAttribute("InvalidUsername", "This username already exists");
		    return "register";
		}
		if (roleId == 0 && teamId == 0)
		{
			model.addAttribute("EmptyRoleId", "Role Id must be selected");
			model.addAttribute("EmptyTeamId", "Team Id must be selected");
		    return "register";
		}
		if (roleId == 0)
		{
			model.addAttribute("EmptyRoleId", "Role Id must be selected");
		    return "register";
		}
		if (teamId == 0)
		{
			model.addAttribute("EmptyTeamId", "Team Id must be selected");
		    return "register";
		}
		
	    employeeService.createEmployee(employee);
	    
	    String message = "New employee " + employee.getUsername() + " was successfully created.";
	    System.out.println(message);
	    
	    return "redirect:/admin/menu";
	  }
      
     
      
      
      
////  Edit   ///////////////////////////////////////////////////////////////////     
      
      @RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
      public String editEmployeePage(@PathVariable String username, Model model)	   

      {
        Employee employee = employeeService.findUser(username);
        model.addAttribute("employee", employee);
        System.out.println("edit");
        return "employeeEdit";
      }
      
    //   @RequestMapping(value = "/edit/{username}", method = RequestMethod.POST)
    //   public String editEmployee(@ModelAttribute Employee employee, Model model)			 				  
    //   {		
    //     Employee employee1 = employeeService.findUser(employee.getUsername());
		
    //     employee1.setName(employee.getName());
    //     employee1.setEmailAddress(employee.getEmailAddress());
		// 	employee1.setRole (employee.getRole());
		// 	employee1.setPhone(employee.getPhone());
		// 	employee1.setTeamId((employee.getTeamId()));
		// 	System.out.println(employee1.getName());
		// 	System.out.println(employee1.getPhone());
    //     employeeService.createEmployee(employee1);
    //     return "redirect:/admin/menu";
    //   }

	@RequestMapping(value = "/edit/{username}", method = RequestMethod.POST)
      public String editEmployee(@ModelAttribute Employee employee, Model model)			 				  
      {		
        Employee employee1 = employeeService.findUser(employee.getUsername());
			
        employee1.setName(employee.getName());	
        employee1.setPhone(employee.getPhone());
        employee1.setEmailAddress(employee.getEmailAddress());
		employee1.setRole (employee.getRole());
		employee1.setTeamId((employee.getTeamId()));
		
        String name = employee1.getName();
		String phone = employee1.getPhone();
		String email = employee1.getEmailAddress();
		
		if (name == "")
		{
			model.addAttribute("EmptyName", "Name is required");
		    return "employeeEdit";
		}
		if (phone == "")
		{
			model.addAttribute("EmptyPhone", "Phone is required");
		    return "employeeEdit";
		} 
		if (email == "")
		{
			model.addAttribute("EmptyEmail", "Email is required");
		    return "employeeEdit";
		}
 
        employeeService.createEmployee(employee1);

        return "redirect:/admin/menu";
      }
      

      
      
      
////  Delete   //////////////////////////////////////////////////////////////
      
      @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
      public String deleteEmployee(@PathVariable String username)
      {
        Employee employee = employeeService.findUser(username);
        employee.setActive(false);
        employeeService.createEmployee(employee);
        return "forward:/admin/menu";
      }
      
      
 
      
}
