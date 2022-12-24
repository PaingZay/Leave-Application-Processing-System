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
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;
import sg.nus.iss.team6.repository.LeaveTypeRepository;
import sg.nus.iss.team6.repository.OvertimeChitRepository;
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
			OvertimeChitRepository otcRepo) {
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
			Employee user4 = employeeRepository.saveAndFlush(
					new Employee("staff1", "123", "Lucy Liu", "+65 9223 4866", "ooo423@gmail.com", staff, 2, 30, true));
			Employee user5 = employeeRepository.saveAndFlush(new Employee("staff2", "123", "Kelvin Chew",
					"+65 8183 3216", "pos511@gmail.com", staff, 1, 30, true));
			Employee user6 = employeeRepository.saveAndFlush(new Employee("staff3", "123", "Rachel Ho", "+65 9516 4854",
					"sos690@gmail.com", staff, 1, 30, true));

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
			app1.setApplicationDate(LocalDateTime.now());
			app1.setLeaveStartDate(LocalDateTime.of(2022, 12, 29, 0, 0, 0, 0));
			app1.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			app1.setApprovalDate(LocalDateTime.now());
			app1.setLeaveType(compensation);
			app1.setComment("");
			app1.setReason("Hello");
			app1.setActive(true);
			app1.setStatus(ApplicationStatus.APPLIED);
			app1.setWorkDelegate(user1);
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
			app3.setApplicationDate(LocalDateTime.now());
			app3.setLeaveStartDate(LocalDateTime.of(2022, 12, 26, 0, 0, 0, 0));
			app3.setLeaveEndDate(LocalDateTime.of(2022, 12, 27, 0, 0, 0, 0));
			app3.setApprovalDate(LocalDateTime.now());
			app3.setLeaveType(annual);
			app3.setComment("");
			app3.setReason("Christmas event.");
			app3.setActive(true);
			app3.setStatus(ApplicationStatus.APPLIED);
			app3.setWorkDelegate(user3);
			app3.setOverseasPhone("31212111");
			applicationRepository.saveAndFlush(app3);

			LeaveApplication app4 = new LeaveApplication();
			app4.setEmployee(user4);
			app4.setApplicationDate(LocalDateTime.now());
			app4.setLeaveStartDate(LocalDateTime.of(2022, 12, 28, 0, 0, 0, 0));
			app4.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			app4.setApprovalDate(LocalDateTime.now());
			app4.setLeaveType(annual);
			app4.setComment("");
			app4.setReason(null);
			app4.setActive(true);
			app4.setStatus(ApplicationStatus.APPLIED);
			app4.setWorkDelegate(user3);
			app4.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app4);

			LeaveApplication app5 = new LeaveApplication();
			app5.setEmployee(user4);
			app5.setApplicationDate(LocalDateTime.now());
			app5.setLeaveStartDate(LocalDateTime.of(2022, 12, 28, 0, 0, 0, 0));
			app5.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			app5.setApprovalDate(LocalDateTime.now());
			app5.setLeaveType(medical);
			app5.setComment("");
			app5.setReason(null);
			app5.setActive(true);
			app5.setStatus(ApplicationStatus.APPLIED);
			app5.setWorkDelegate(user4);
			app5.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app5);

			LeaveApplication app6 = new LeaveApplication();
			app6.setEmployee(user5);
			app6.setApplicationDate(LocalDateTime.now());
			app6.setLeaveStartDate(LocalDateTime.of(2022, 12, 28, 0, 0, 0, 0));
			app6.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			app6.setApprovalDate(LocalDateTime.now());
			app6.setLeaveType(compensation);
			app6.setComment("");
			app6.setReason(null);
			app6.setActive(true);
			app6.setStatus(ApplicationStatus.APPLIED);
			app6.setWorkDelegate(user5);
			app6.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app6);

			LeaveApplication app7 = new LeaveApplication();
			app7.setEmployee(user6);
			app7.setApplicationDate(LocalDateTime.now());
			app7.setLeaveStartDate(LocalDateTime.of(2022, 12, 28, 0, 0, 0, 0));
			app7.setLeaveEndDate(LocalDateTime.of(2022, 12, 30, 0, 0, 0, 0));
			app7.setApprovalDate(LocalDateTime.now());
			app7.setLeaveType(medical);
			app7.setComment("");
			app7.setReason(null);
			app7.setActive(true);
			app7.setStatus(ApplicationStatus.APPLIED);
			app7.setWorkDelegate(user5);
			app7.setOverseasPhone("312121121");
			applicationRepository.saveAndFlush(app7);
			
			
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
			
		};
	}

}
