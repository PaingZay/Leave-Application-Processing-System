package sg.nus.iss.team6.controller;

import java.util.List;

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

import sg.nus.iss.team6.controller.exception.LeaveTypeNotFound;
import sg.nus.iss.team6.controller.exception.PHNotFound;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.service.PublicHolidayService;
import sg.nus.iss.team6.validator.PHValidator;

@Controller
@RequestMapping(value = "/admin/publicholiday")
public class PHController {
  @Autowired
  private PublicHolidayService publicHolidayService;

  @Autowired
  private PHValidator phValidator;

  @InitBinder("ph")
  private void initPHBinder(WebDataBinder binder) {
    binder.addValidators(phValidator);
  }
  

  /**
   * PH CRUD OPERATIONS
   * 
   * @return
   */
  @GetMapping(value = "/list")
  public String publicHolidayList( Model model) {
 
    List<PublicHoliday> puhList = publicHolidayService.findAllHoliday();
    model.addAttribute("phList", puhList);  
    return "ph-list";
  }

  @GetMapping("/create")
  public String newphPage(Model model) {
    model.addAttribute("ph", new PublicHoliday());
    
    return "ph-new";
  }

  @PostMapping("/create")
  public String createNewph(@ModelAttribute @Valid PublicHoliday ph, BindingResult result,
      HttpSession session) {
    if (result.hasErrors()) {
      return "ph-new";
    }
    publicHolidayService.createPublicHoliday(ph);
    
    String message = "New pubic holiday " + ph.getId() + " was successfully created.";
    System.out.println(message);
    
    return "redirect:/admin/publicholiday/list";
  }
  
  @GetMapping("/edit/{id}")
	public String editPublicHoliday(@PathVariable Integer id, Model model) {
	  PublicHoliday ph = publicHolidayService.findPublicHoliday(id);
		model.addAttribute("publicholiday",ph);
		
		return "ph-edit";
	}

	@PostMapping("/edit/{id}")
	public String editLeaveType(@ModelAttribute @Valid PublicHoliday publicholiday, BindingResult result, 
			@PathVariable String id) throws PHNotFound {
		if (result.hasErrors()) {
			return "ph-edit";
		}

		String message = "public holiday was successfully updated.";
		System.out.println(message);
		publicHolidayService.changePublicHoliday(publicholiday);
		
		return "redirect:/admin/publicholiday/list";
	}
  
}
