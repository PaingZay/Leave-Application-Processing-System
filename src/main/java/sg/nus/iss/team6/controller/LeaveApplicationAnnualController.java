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

@Controller
@RequestMapping(value = "/leave")
public class LeaveApplicationAnnualController {

	@Autowired
	private LeaveApplicationService laService;

	@Autowired
	private EmployeeService eService;

	@Autowired
	private RoleService rService;

	@Autowired
	private PublicHolidayService phService;

	// TODO: refactor to autowired
	private LeaveTypeService ltService = new LeaveTypeService();

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

	@GetMapping("/apply/annual")
	public String newAnnualLeaveApplication(Model model) {

		model.addAttribute("leaveAppForm", new LeaveAppForm());

		return "leaveApplicationAnnual";
	}

	@PostMapping("/apply/annual")
	public String createNewAnnualLeaveApplication(@ModelAttribute @Valid LeaveAppForm leaveAppForm,
			BindingResult result, HttpSession session) {

		// check for errors
		if (result.hasErrors()) {
			return "leaveApplicationAnnual";
		}

		// TODO combine with UserSession
		// hardcode EmployeeId first
		int currentUserId = leaveAppForm.getApplicantId();

		Employee currentUser = eService.findEmployee(currentUserId);
		LeaveType leaveType = ltService.findLeaveTypeByNameAndRole("Annual", currentUser.getRole());
		Double maxEntitlement = leaveType.getMaxEntitlement();
		Employee desiredEmployee = eService.findEmployeeByName(leaveAppForm.getWorkDelegate());

		// get year of leave
		Integer leaveAppStartYear = leaveAppForm.getLeaveStartDate().getYear();

		LocalDateTime leaveStart = leaveAppForm.getLeaveStartDate().atTime(leaveAppForm.getLeaveStartTime(), 0, 0);
		LocalDateTime leaveEnd = leaveAppForm.getLeaveEndDate().atTime(leaveAppForm.getLeaveEndTime(), 0, 0);
		long leaveStartUnix = ldt.getUnixTimeStampSG(leaveStart);
		long leaveEndUnix = ldt.getUnixTimeStampSG(leaveEnd);

		Integer leaveDuration = (int) Duration.between(leaveStart, leaveEnd).toDays();

		if (leaveDuration <= 14) {
			// get list of all public holidays, weekends
			List<PublicHoliday> publicHolidays = phService.findAllPublicHolidays();

			List<PublicHoliday> overlappingPh = new ArrayList<>();
			List<PublicHoliday> overlappingPhFound = new ArrayList<>();
			List<LocalDateTime> overlappingWeekends = ldt.getWeekendDates(leaveStart, leaveEnd);
			List<LocalDateTime> overlappingWkendFound = new ArrayList<>();

			Map<LocalDateTime, Integer> overlappingUnified = new HashMap<LocalDateTime, Integer>();

			// assign these in case there are no conflicting PH/Weekends
			LocalDateTime newLeaveEndDateLdt = leaveEnd;
			long newLeaveEndDateUnix = leaveEndUnix;
			// LocalDateTime newLeaveStartDateLdt;

			// create a list for conflicting public holidays
			// check if it is empty, not null?

			// check for overlaps with PH
			// add to new list
			for (PublicHoliday phol : publicHolidays) {

				if (ldt.isOverlap(leaveStart, leaveEnd, phol.getLDTByYear(leaveAppStartYear),
						phol.getLDTEndByYear(leaveAppStartYear))) {
					overlappingPh.add(phol);
				}
			}

			// Check for PH overlaps with weekend
			for (PublicHoliday phol : overlappingPh) {

				for (LocalDateTime wkend : overlappingWeekends) {

					if (ldt.isOverlap(phol.getLDTByYear(leaveAppStartYear), phol.getLDTEndByYear(leaveAppStartYear),
							wkend, wkend.plusDays(2))) {

						long newUniEndDateUnix;
						LocalDateTime newUniStartDateLdt;
						LocalDateTime newUniEndDateLdt;

						long toAdd = ldt.getOverlapInSeconds(phol.getLDTByYear(leaveAppStartYear),
								phol.getLDTEndByYear(leaveAppStartYear), wkend, wkend.plusDays(2));
						long pholEndUnix = ldt.getUnixTimeStampSG(phol.getLDTEndByYear(leaveAppStartYear));
						long wkendEndUnix = ldt.getUnixTimeStampSG(wkend.plusDays(2));

						if (pholEndUnix > wkendEndUnix) {
							newUniEndDateUnix = pholEndUnix + toAdd;
						} else {
							newUniEndDateUnix = wkendEndUnix + toAdd;
						}

						newUniStartDateLdt = ldt.getMin(phol.getLDTByYear(leaveAppStartYear), wkend);
						newUniEndDateLdt = ldt.getUnixTimeStampSGInLdt(newUniEndDateUnix);

						Integer daysBetween = (int) Duration.between(newUniStartDateLdt, newUniEndDateLdt).toDays();

						overlappingUnified.put(newUniStartDateLdt, daysBetween);
						overlappingPhFound.add(phol);
						overlappingWkendFound.add(wkend);
					}

				}

			}
			// circumvent concurrency error
			overlappingPh.removeAll(overlappingPhFound);
			overlappingWeekends.removeAll(overlappingWkendFound);
			// purge to re-add
			overlappingPhFound = new ArrayList<>();
			overlappingWkendFound = new ArrayList<>();

			// add everything to unified list
			for (PublicHoliday phol : overlappingPh) {
				overlappingUnified.put(phol.getLDTByYear(leaveAppStartYear), phol.getPhLength());
				overlappingPhFound.add(phol);
			}
			for (LocalDateTime wkend : overlappingWeekends) {
				overlappingUnified.put(wkend, 2);
				overlappingWkendFound.add(wkend);
			}
			// circumvent concurrency error
			overlappingPh.removeAll(overlappingPhFound);
			overlappingWeekends.removeAll(overlappingWkendFound);

			//------------------------------------------------------------
			//it will keep resetting if it's a blind loop
			//how to keep track? make newLeaveEndDateUnix keep extending?
				//if ALL the holiday.endTime < leave.endTime, just append all overlaps
				//can only have one holiday.endTime > leave.endTime
				
			Map<LocalDateTime, Integer> toAddAtEnd = new HashMap<LocalDateTime, Integer>();
			
			for (Map.Entry<LocalDateTime, Integer> entry : overlappingUnified.entrySet()) {

				long toAdd = ldt.getOverlapInSeconds(leaveStart, leaveEnd, entry.getKey(),
						entry.getKey().plusDays(entry.getValue()));
				long uniEndUnix = ldt.getUnixTimeStampSG(entry.getKey().plusDays(entry.getValue()));
				
				if(uniEndUnix > leaveEndUnix) {
					toAddAtEnd.put(entry.getKey(),entry.getValue() );
				}
				else {
					//already assigned above
					newLeaveEndDateUnix += toAdd;
				}
			}
			if(!toAddAtEnd.isEmpty()) {
				Map.Entry<LocalDateTime, Integer> entry = toAddAtEnd.entrySet().iterator().next();
				LocalDateTime uniDate = entry.getKey();
				Integer dayLength = entry.getValue();
				
				//only one or zero values can extend past deadline 
				long toAdd = ldt.getOverlapInSeconds(leaveStart, 
													ldt.getUnixTimeStampSGInLdt(newLeaveEndDateUnix), 
													uniDate,
													uniDate.plusDays(dayLength));
				long uniEndUnix = ldt.getUnixTimeStampSG(uniDate.plusDays(dayLength));
				
				if(uniEndUnix > newLeaveEndDateUnix) {
					newLeaveEndDateUnix = uniEndUnix + toAdd;
				}
				else {
					newLeaveEndDateUnix+=toAdd;
				}
			}
			
			
			newLeaveEndDateLdt = ldt.getUnixTimeStampSGInLdt(newLeaveEndDateUnix);

			LeaveApplication myLA = leaveAppForm.convertToLA(LocalDateTime.now(), leaveType, desiredEmployee,
					newLeaveEndDateLdt);
			laService.createLeaveApplication(myLA);

			currentUser.addLeaveApplication(myLA);
			eService.changeEmployee(currentUser);

			String message = "New leave application was successfully created, excluding PH and weekends.";
			System.out.println(message);

			return "redirect:/leave/apply/annual";
		}
		else {
			LeaveApplication myLA = leaveAppForm.convertToLA(LocalDateTime.now(), leaveType, desiredEmployee,
					leaveEnd);
			laService.createLeaveApplication(myLA);

			currentUser.addLeaveApplication(myLA);
			eService.changeEmployee(currentUser);

			String message = "New annual leave application was successfully created.";
			System.out.println(message);

			return "redirect:/leave/apply/annual";
		}

	}

//  @GetMapping("/course/edit/{id}")
//  public String editCoursePage(@PathVariable Integer id, Model model) {
//    Course course = courseService.findCourse(id);
//    model.addAttribute("course", course);
//    
//    return "course-edit";
//  }
//
//  @PostMapping("/course/edit/{id}")
//  public String editCourse(@ModelAttribute @Valid Course course, BindingResult result, @PathVariable Integer id,
//      HttpSession session) throws CourseNotFound {
//    if (result.hasErrors())
//      return "course-edit";
//    
//    System.out.println("DATES****" + course.getFromDate() + course.getToDate());
//    
//    UserSession usession = (UserSession) session.getAttribute("usession");
//    course.setEmployeeId(usession.getEmployee().getEmployeeId());
//    course.setStatus(CourseEventEnum.UPDATED);
//    
//    courseService.changeCourse(course);
//    
//    return "redirect:/staff/course/history";
//  }
//
//  @RequestMapping(value = "/course/withdraw/{id}")
//  public String deleteCourse(@PathVariable Integer id) throws CourseNotFound {
//    Course course = courseService.findCourse(id);
//    
//    course.setStatus(CourseEventEnum.WITHDRAWN);
//    courseService.changeCourse(course);
//
//    String message = "Course " + course.getCourseId() + " was successfully withdrawn.";
//    System.out.println(message);
//    
//    return "redirect:/staff/course/history";
//  }

}
