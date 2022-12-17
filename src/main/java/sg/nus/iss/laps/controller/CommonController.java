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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.laps.model.Application;
import sg.nus.iss.laps.model.Employee;
import sg.nus.iss.laps.model.UserSession;
import sg.nus.iss.laps.service.ApplicationService;
import sg.nus.iss.laps.service.EmployeeService;
import sg.nus.iss.laps.service.UserService;

@Controller
public class CommonController {
	
	  @Autowired
	  private ApplicationService applicationService;
	
	  @Autowired
	  private UserService userService;
	  
		@GetMapping("/login")
		  public String login() {
		    return "login";
		  }
		  
		  @PostMapping("/login") 
		  public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password, 
		                                Model model, HttpSession session) 
		  {
			  Employee e = userService.findByName(username,password);
			  String name = e.getUsername();
			  String pass = e.getPassword();
			  int role = e.getRoleId();
			  
			 
			  
			  System.out.println(name);
			  
			  if (name.equals(username) && pass.equals(password) && (role == 1)) 
			  {
			      session.setAttribute("username", username);
			      return "redirect:/admin/menu";
			  }
			  
			  else if (name.equals(username) && pass.equals(password) && (role == 2)) 
			  {
			      session.setAttribute("username", username);
			      return "redirect:/manager/menu";
			  }
			  
			  else if (name.equals(username) && pass.equals(password) && (role == 3)) 
			  {
			      session.setAttribute("username", username);
			      return "redirect:/staff/menu";
			  }
			  
		    return "login";
		  }
		  
		  
		@GetMapping("/logout")
		  public String logout(HttpSession session) {
		    
			  session.invalidate();
			    
			  return "redirect:/login";
		}
	  
	  
	  
	  
	  
	  
	  
	  //This is my code
	  
//	  @RequestMapping(value = "/home/authenticate")//get user object containing username and password form view
//	  public String authenticate(@ModelAttribute("user") @Valid Employee employee, BindingResult bindingResult, Model model,
//	      HttpSession session) {
//	    if (bindingResult.hasErrors()) {
//	      return "login";
//	    } 
//	    
//	    //Use username and password we got from view and pass the parameters to userService.authenticate function
//	    //Then we will have user object containing every attributes in User class.
//	    Employee e = userService.authenticate(employee.getUsername(), employee.getPassword());
//	    if (e == null) {
//	      model.addAttribute("loginMessage", "Incorrect username/password");
//	      return "login";
//	    }
//	    
//	    // Login ok, let's put the user info into a session
//	    // a. The user object itself
//	    UserSession userSession = new UserSession();
//	    userSession.setEmployee(e);
//	    
//	    // c. The subordinates
//	    List<Employee> subordinates = employeeService.findSubordinates(e.getTeamId());//Actually not findSubordinate function it's for temporary //Right here I just pass team ID to find subordinates by using teamId
//	    if (subordinates != null) {
//	      userSession.setSubordinates(subordinates);
//	    }
//	    
//	    session.setAttribute("usession", userSession);//set the userSession object using key and value
//	    
//	    if(e.getRoleId()==1){
//	    	return "redirect:/admin/employee/list";
//	    }
//	    if (e.getRoleId()==(2)) {
//		      return "redirect:/manager/pending";
//		}
	    
//	    return "redirect:/staff/course/history";
//	  }
}
