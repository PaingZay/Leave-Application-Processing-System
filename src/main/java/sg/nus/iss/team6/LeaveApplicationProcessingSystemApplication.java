package sg.nus.iss.team6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.OvertimeChit;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;
import sg.nus.iss.team6.repository.LeaveTypeRepository;
import sg.nus.iss.team6.repository.OvertimeChitRepository;
import sg.nus.iss.team6.repository.PublicHolidayRepository;
import sg.nus.iss.team6.repository.RoleRepository;
import sg.nus.iss.team6.repository.EmployeeRepository;

@SpringBootApplication
public class LeaveApplicationProcessingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveApplicationProcessingSystemApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("SGT"));
	}

	@Bean
	CommandLineRunner initData(
			LeaveApplicationRepository applicationRepository, 
			EmployeeRepository employeeRepository,
			RoleRepository roleRepository, 
			LeaveTypeRepository leaveTypeRepository,
			OvertimeChitRepository otcRepo,
			PublicHolidayRepository publicHolidayRepo) {
		return (args) -> {

			Role admin = roleRepository.save(new Role(1, 2, "admin", "Administrator", true));
			Role staff = roleRepository.save(new Role(3, 1, "staff", "Staff members", true));
			Role manager = roleRepository.save(new Role(2, 3, "manager", "Manager", true));

			Employee user1 = employeeRepository.saveAndFlush(new Employee("admin", "123",
					"Paing Zay The God Amongst Men", "+65 8951 3002", "PZ@gmail.com", admin, 1, 20.0, true));
			Employee user2 = employeeRepository.saveAndFlush(new Employee("manager", "123", "Michael Boris",
					"+65 9196 5446", "micheal@gmail.com", manager, 1, 20.0, true));
			
			Employee user3 = employeeRepository.saveAndFlush(new Employee("staff", "123", "John Chan Hao",
					"+65 9866 4866", "john@gmail.com", staff, 1, 30, true));
			
			Employee user4 = employeeRepository.saveAndFlush(new Employee("staff1", "123", "Team 2 Escapee", "+65 9223 4866", "ogo423@gmail.com", staff, 2, 30, true));
			
			Employee user5 = employeeRepository.saveAndFlush(new Employee("staff2", "123", "Kelvin Chew",
					"+65 8183 3216", "pos511@gmail.com", staff, 1, 30, true));
			Employee user6 = employeeRepository.saveAndFlush(new Employee("staff3", "123", "Rachel Ho", "+65 9516 4854",
					"sos690@gmail.com", staff, 1, 30, true));
			
			Employee user7 = employeeRepository.saveAndFlush(
					new Employee("runner", "123", "Lucy Liu", "+65 9883 4866", "lemonPie423@gmail.com", staff, 1, 30, true));

			LeaveType annual = leaveTypeRepository
					.saveAndFlush(new LeaveType("Annual", 14.0, 0.5, "Medical Leave Description", true, admin));
			LeaveType medical = leaveTypeRepository
					.save(new LeaveType("Medical", 14.0, 0.5, "Annual Leave", true, admin));
			LeaveType compensation = leaveTypeRepository
					.save(new LeaveType("Compensation", 14.0, 0.5, "Annual Leave", true, admin));

			LeaveType annualS = leaveTypeRepository
					.saveAndFlush(new LeaveType("Annual", 14.0, 0.5, "Annual Leave for Staff", true, staff));
			LeaveType medicalS = leaveTypeRepository
					.saveAndFlush(new LeaveType("Medical", 60.0, 0.5, "Medical leave", true, staff));
			LeaveType compensationS = leaveTypeRepository
					.saveAndFlush(new LeaveType("Compensation", 0.0, 0.5, "Compensation leave", true, staff));

			LeaveApplication app1 = new LeaveApplication();
			app1.setEmployee(user1);
			app1.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app1.setLeaveStartDate(LocalDateTime.of(2023, 12, 29, 0, 0, 0, 0));
			app1.setLeaveEndDate(LocalDateTime.of(2023, 12, 30, 0, 0, 0, 0));
			app1.setApprovalDate(LocalDateTime.now());
			app1.setLeaveType(compensation);
			app1.setComment(null);
			app1.setReason("Meeting with Jotaro.");
			app1.setActive(true);
			app1.setStatus(ApplicationStatus.APPLIED);
			app1.setWorkDelegate(user7);
			app1.setOverseasPhone("231456");
			applicationRepository.saveAndFlush(app1);

			// LeaveApplication app2=new LeaveApplication();
			// app2.setEmployee(user2);
			// app2.setApplicationDate(LocalDateTime.now());
			// app2.setLeaveStartDate(LocalDateTime.of(2022, 12, 29, 0, 0, 0, 0));
			// app2.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			// app2.setApprovalDate(LocalDateTime.now());
			// app2.setLeaveType(medical);
			// app2.setComment(null);
			// app2.setReason("No Reason");
			// app2.setActive(true);
			// app2.setStatus(ApplicationStatus.APPLIED);
			// app2.setWorkDelegate(user2);
			// app2.setOverseasPhone("12345");
			// applicationRepository.saveAndFlush(app2);

			//USE THIS
			//user3- john- has leave from 26th to 27th (1 day)
			LeaveApplication app3 = new LeaveApplication();
			app3.setEmployee(user3);
			app3.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app3.setLeaveStartDate(LocalDateTime.of(2023, 5, 26, 0, 0, 0, 0));
			app3.setLeaveEndDate(LocalDateTime.of(2023, 5, 27, 0, 0, 0, 0));
			app3.setApprovalDate(LocalDateTime.now());
			app3.setLeaveType(annual);
			app3.setComment(null);
			app3.setReason("May day.");
			app3.setActive(true);
			app3.setStatus(ApplicationStatus.APPLIED);
			app3.setWorkDelegate(user7);
			app3.setOverseasPhone("31212111");
			applicationRepository.saveAndFlush(app3);

			LeaveApplication app4 = new LeaveApplication();
			app4.setEmployee(user4);
			app4.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app4.setLeaveStartDate(LocalDateTime.of(2023, 12, 28, 0, 0, 0, 0));
			app4.setLeaveEndDate(LocalDateTime.of(2023, 12, 30, 0, 0, 0, 0));
			app4.setApprovalDate(LocalDateTime.now());
			app4.setLeaveType(annual);
			app4.setComment(null);
			app4.setReason("Escaping from department 2.");
			app4.setActive(true);
			app4.setStatus(ApplicationStatus.APPLIED);
			app4.setWorkDelegate(null);
			app4.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app4);

			LeaveApplication app6 = new LeaveApplication();
			app6.setEmployee(user5);
			app6.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app6.setLeaveStartDate(LocalDateTime.of(2023, 12, 28, 0, 0, 0, 0));
			app6.setLeaveEndDate(LocalDateTime.of(2023, 12, 30, 0, 0, 0, 0));
			app6.setApprovalDate(LocalDateTime.now());
			app6.setLeaveType(compensation);
			app6.setComment(null);
			app6.setReason("Cake day with the family.");
			app6.setActive(true);
			app6.setStatus(ApplicationStatus.APPLIED);
			app6.setWorkDelegate(user1);
			app6.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app6);

			LeaveApplication app7 = new LeaveApplication();
			app7.setEmployee(user6);
			app7.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app7.setLeaveStartDate(LocalDateTime.of(2023, 12, 28, 0, 0, 0, 0));
			app7.setLeaveEndDate(LocalDateTime.of(2023, 12, 30, 0, 0, 0, 0));
			app7.setApprovalDate(LocalDateTime.now());
			app7.setLeaveType(medical);
			app7.setComment(null);
			app7.setReason("Off-peak leave.");
			app7.setActive(true);
			app7.setStatus(ApplicationStatus.APPLIED);
			app7.setWorkDelegate(user5);
			app7.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app7);
			
			LeaveApplication app8 = new LeaveApplication();
			app8.setEmployee(user7);
			app8.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app8.setLeaveStartDate(LocalDateTime.of(2023, 11, 28, 0, 0, 0, 0));
			app8.setLeaveEndDate(LocalDateTime.of(2023, 11, 30, 0, 0, 0, 0));
			app8.setApprovalDate(LocalDateTime.now());
			app8.setLeaveType(medical);
			app8.setComment(null);
			app8.setReason("Just emigrated! Need to pack some things.");
			app8.setActive(true);
			app8.setStatus(ApplicationStatus.APPLIED);
			app8.setWorkDelegate(user5);
			app8.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app8);
			
			LeaveApplication app5 = new LeaveApplication();
			app5.setEmployee(user7);
			app5.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app5.setLeaveStartDate(LocalDateTime.of(2023, 12, 28, 0, 0, 0, 0));
			app5.setLeaveEndDate(LocalDateTime.of(2023, 12, 30, 0, 0, 0, 0));
			app5.setApprovalDate(LocalDateTime.now());
			app5.setLeaveType(medical);
			app5.setComment(null);
			app5.setReason("Looking for sandy beaches.");
			app5.setActive(true);
			app5.setStatus(ApplicationStatus.APPLIED);
			app5.setWorkDelegate(user1);
			app5.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app5);
			

			LeaveApplication app9 = new LeaveApplication();
			app9.setEmployee(user5);
			app9.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app9.setLeaveStartDate(LocalDateTime.of(2023, 5, 28, 0, 0, 0, 0));
			app9.setLeaveEndDate(LocalDateTime.of(2023, 5, 30, 0, 0, 0, 0));
			app9.setApprovalDate(LocalDateTime.now());
			app9.setLeaveType(annual);
			app9.setComment(null);
			app9.setReason("Rejoicing!");
			app9.setActive(true);
			app9.setStatus(ApplicationStatus.APPLIED);
			app9.setWorkDelegate(user1);
			app9.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app9);
			
			LeaveApplication app10 = new LeaveApplication();
			app10.setEmployee(user3);
			app10.setApplicationDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0, 0));
			app10.setLeaveStartDate(LocalDateTime.of(2023, 6, 26, 0, 0, 0, 0));
			app10.setLeaveEndDate(LocalDateTime.of(2023, 6, 27, 0, 0, 0, 0));
			app10.setApprovalDate(LocalDateTime.now());
			app10.setLeaveType(annual);
			app10.setComment(null);
			app10.setReason("June holidays.");
			app10.setActive(true);
			app10.setStatus(ApplicationStatus.APPLIED);
			app10.setWorkDelegate(user7);
			app10.setOverseasPhone("31212111");
			applicationRepository.saveAndFlush(app10);
			
			//----------------------
			LocalDateTime otc001s=LocalDateTime.of(2022, 5, 8,2,0,0,0);
			LocalDateTime otc001e=LocalDateTime.of(2022, 5, 8,8,0,0,0);
			LocalDateTime otc002s=LocalDateTime.of(2022, 5, 9,2,0,0,0);
			LocalDateTime otc002e=LocalDateTime.of(2022, 5, 9,8,0,0,0);
			LocalDateTime otc003s=LocalDateTime.of(2022, 5, 10,6,0,0,0);
			LocalDateTime otc003e=LocalDateTime.of(2022, 5, 10,8,0,0,0);
			
			//should have 4h in 2022 (6*2 approved, 2 rejected)
			OvertimeChit otc001 = otcRepo.save(new OvertimeChit(otc001s,otc001e,ApplicationStatus.APPROVED)); 
			OvertimeChit otc002 = otcRepo.save(new OvertimeChit(otc002s,otc002e,ApplicationStatus.APPROVED));
			OvertimeChit otc003 = otcRepo.save(new OvertimeChit(otc003s,otc003e,ApplicationStatus.REJECTED));
			
			List<OvertimeChit> otcToAdd=new ArrayList<OvertimeChit>();
			otcToAdd.add(otc001);
			otcToAdd.add(otc002);
			otcToAdd.add(otc003);
			user3.setOvertimeChits(otcToAdd);
			
			employeeRepository.save(user3);
			
			PublicHoliday christmas = publicHolidayRepo.save(new PublicHoliday("Christmas Day", 25, 12, 1));
			PublicHoliday diwali = publicHolidayRepo.save(new PublicHoliday("Deepavali, Festival of Lights", 24, 10, 1));
			PublicHoliday companyEvent1 = publicHolidayRepo.save(new PublicHoliday("Annual Fiesta 1", 26, 10, 1));	
			PublicHoliday companyEvent2 = publicHolidayRepo.save(new PublicHoliday("Annual Fiesta 2", 10, 1, 2));	
			
		};
	}

}
