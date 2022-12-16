package sg.nus.iss.team6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.model.LeaveType;
import sg.nus.iss.team6.model.Role;
import sg.nus.iss.team6.repository.EmployeeRepository;
import sg.nus.iss.team6.repository.LeaveTypeRepository;
import sg.nus.iss.team6.repository.RoleRepository;

@SpringBootApplication
public class SpringCaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCaApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(RoleRepository roleRepository, 
                            EmployeeRepository employeeRepository,
                           LeaveTypeRepository leaveTypeRepository) {
    return (args) -> {
      // Add a few Roles
		
      Role Staff=roleRepository.save(new Role(2000, 2,"admin", "Administrator", true));
      Role Admin=roleRepository.save(new Role(1000,1,"staff", "Staff members",true));
      Role Manager=roleRepository.save(new Role(3000,3,"manager", "Manager",true));
		 
      
		/*
		 * Role Staff=new Role(1,1,"staff", "Staff members",true); Role Admin=new
		 * Role(2, 2,"admin", "Administrator", true); Role Manager=new
		 * Role(3,3,"manager", "Manager",true);
		 */
      
      // Add a few Employees
      employeeRepository.save(new Employee(6001,"jerry","password","Jerry Wang","98765432", "admin@nus.com",2,0.5,Admin,true));
      employeeRepository.save(new Employee(6002,"cherwah","password","Cher Wah","96543211","staff02@nus.com",1,1.5,Staff,true));
      employeeRepository.save(new Employee(6003,"tin","password", "Nguyen Tri Tin", "93456785","staff03@nus.com",1,2.5,Staff,true));
      employeeRepository.save(new Employee(6004,"esther", "password", "Esther Tan", "93456445","manager@nus.com",3,3.5,Manager,true));
      employeeRepository.save(new Employee(6005,"yuenkwan","password", "Yuen Kwan", "93422445","staff01@nus.com",1,4.5,Staff,true));
      
      // Add a Leave Types
      leaveTypeRepository.save(new LeaveType(996,"al",14,0.5,"Annual Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(998,"al_manager",18,0.5,"Annual Leave",true,Manager));
      leaveTypeRepository.save(new LeaveType(997,"al_senior",16,0.5,"Annual Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(995,"ml",60,1,"Medical Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(994,"ml_senior",70,1,"Medical Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(993,"ml_manager",80,1,"Medical Leave",true,Manager));
      leaveTypeRepository.save(new LeaveType(995,"cl",2,1,"Compensation Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(994,"cl_senior",3,1,"Compensation Leave",true,Staff));
      leaveTypeRepository.save(new LeaveType(993,"cl_manager",4,1,"Compensation Leave",true,Manager)); 
    };
	}
}

