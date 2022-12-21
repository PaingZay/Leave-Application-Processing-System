package sg.nus.iss.team6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.OvertimeChit;
import sg.nus.iss.team6.model.PublicHoliday;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.model.Team;
import sg.nus.iss.team6.repository.*;
import sg.nus.iss.team6.util.LeaveStatusEnum_ToCompile;
import sg.nus.iss.team6.util.ApplicationStatus;

@SpringBootApplication
public class SpringCaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCaApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(EmployeeRepository employeeRepo, 
			LeaveApplicationRepository leaveApplicationRepo, 
			LeaveTypeRepository leaveTypeRepo, 
			PublicHolidayRepository publicHolidayRepo,
			RoleRepository roleRepo,
			TeamRepository teamRepo,
			OvertimeChitRepository otcRepo) {
		return (args) -> {
			// Add Roles
			Role adminRole = roleRepo.save(new Role(-1, "Admin", "Admin Role"));
			Role userRole = roleRepo.save(new Role(0, "User", "User Role"));
			Role staffRole = roleRepo.save(new Role(1, "Staff", "Staff Role"));
			Role managerRole = roleRepo.save(new Role(2, "Manager", "Manager Role"));
			
			
			// Add Leave Types
			LeaveType annualS =new LeaveType("Annual", 14.0, null, "Annual Leave for Staff");
			LeaveType annualM = new LeaveType("Annual", 18.0, null, "Annual Leave for Managers");
			LeaveType medical = new LeaveType("Medical", 60.0, null, "Medical leave");
			LeaveType compensation = new LeaveType("Compensation", null, 0.5, "Compensation leave");
			leaveTypeRepo.saveAndFlush(annualS);
			leaveTypeRepo.saveAndFlush(annualM);
			leaveTypeRepo.saveAndFlush(medical);
			leaveTypeRepo.saveAndFlush(compensation);
			
			//oneToMany mapping for leaveTypes stored/owned in Roles
			//add default admin & staff leave types
			List<LeaveType> staffLeaveTypes=new ArrayList<LeaveType>();
			staffLeaveTypes.add(annualS);
			staffLeaveTypes.add(medical);
			staffLeaveTypes.add(compensation);
			adminRole.setLeaveTypes(staffLeaveTypes);
			staffRole.setLeaveTypes(staffLeaveTypes);
			
			//add default manager leave types
			List<LeaveType> managerLeaveTypes=new ArrayList<LeaveType>();
			managerLeaveTypes.add(annualM);
			managerLeaveTypes.add(medical);
			managerLeaveTypes.add(compensation);
			managerRole.setLeaveTypes(managerLeaveTypes);
			
			roleRepo.saveAndFlush(adminRole);
			roleRepo.saveAndFlush(staffRole);
			roleRepo.saveAndFlush(managerRole);
			
			//add team
			Team alpha = teamRepo.save(new Team("Alpha","Striving for excellence!"));
			
			//add employees
			Employee john=employeeRepo.save(new Employee("Jonjon1", "Password", "John Chan Hao", "9344 6789", adminRole));
			Employee adeline=employeeRepo.save(new Employee("Lunchlady2", "Password", "Adeline Ong", "8445 3389", userRole));
			Employee georgie=employeeRepo.save(new Employee("Elegant1", "Password", "George Koh", "9335 3389", managerRole));
			
			List<Employee> toAdd=new ArrayList<Employee>();
			toAdd.add(john);
			toAdd.add(adeline);
			toAdd.add(georgie);
			
			//set team members
			//need to saveAndFlush whenever updating lists
			alpha.setTeamMembers(toAdd);
			teamRepo.saveAndFlush(alpha);
			
			PublicHoliday christmas = publicHolidayRepo.save(new PublicHoliday("Christmas Day", 25, 12, 1));
			PublicHoliday diwali = publicHolidayRepo.save(new PublicHoliday("Deepavali, Festival of Lights", 24, 10, 1));
			PublicHoliday companyEvent1 = publicHolidayRepo.save(new PublicHoliday("Annual Fiesta 1", 26, 10, 1));	
			PublicHoliday companyEvent2 = publicHolidayRepo.save(new PublicHoliday("Annual Fiesta 2", 10, 1, 2));	
			
//			System.out.println(christmas.getPHDay(2022));
//			System.out.println(christmas.getPHDayIndex(2022));
			
			//create a leave application
			LocalDateTime applyDate=LocalDateTime.of(2022, 1, 8, 0, 0, 0, 0);
			LocalDateTime lSDate=LocalDateTime.of(2022, 1, 10, 0, 0, 0, 0);
			LocalDateTime lEDate=LocalDateTime.of(2022, 1, 23, 0, 0, 0, 0);

			
			LeaveApplication johnsLeave = leaveApplicationRepo.save(new LeaveApplication(applyDate, lSDate, lEDate, "Spending the holidays with my children.",annualS));
			john.addLeaveApplication(johnsLeave);
			//always flush owning side
			employeeRepo.saveAndFlush(john);
			
			LocalDateTime otc001s=LocalDateTime.of(2022, 5, 8,6,0,0,0);
			LocalDateTime otc001e=LocalDateTime.of(2022, 5, 8,8,0,0,0);
			LocalDateTime otc002s=LocalDateTime.of(2022, 5, 9,6,0,0,0);
			LocalDateTime otc002e=LocalDateTime.of(2022, 5, 9,8,0,0,0);
			LocalDateTime otc003s=LocalDateTime.of(2022, 5, 10,6,0,0,0);
			LocalDateTime otc003e=LocalDateTime.of(2022, 5, 10,8,0,0,0);
			
			//should have 4h in 2022 (2*2 approved, 2 rejected)
			OvertimeChit otc001 = otcRepo.save(new OvertimeChit(otc001s,otc001e,ApplicationStatus.APPROVED)); 
			OvertimeChit otc002 = otcRepo.save(new OvertimeChit(otc002s,otc002e,ApplicationStatus.APPROVED));
			OvertimeChit otc003 = otcRepo.save(new OvertimeChit(otc003s,otc003e,ApplicationStatus.REJECTED));
			
			List<OvertimeChit> otcToAdd=new ArrayList<OvertimeChit>();
			otcToAdd.add(otc001);
			otcToAdd.add(otc002);
			otcToAdd.add(otc003);
			john.setOvertimeChits(otcToAdd);
			
			employeeRepo.saveAndFlush(john);
			

			
			
			
			
			
			
			// esther.setRoleSet(Arrays.asList(staffRole, managerRole));

			// userRepository.saveAndFlush(??);

		};
	}
}
