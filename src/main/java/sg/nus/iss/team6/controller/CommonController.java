package sg.nus.iss.team6.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.service.UserService;

@Controller
public class CommonController {
	
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
}
