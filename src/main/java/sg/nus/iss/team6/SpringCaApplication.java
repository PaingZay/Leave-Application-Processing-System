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
      roleRepository.save(new Role(2000, 2,"admin", "Administrator", true));
      roleRepository.save(new Role(1000,1,"staff", "Staff members",true));
      roleRepository.save(new Role(3000,3,"manager", "Manager",true));
      
      // Add a few Employees
      employeeRepository.save(new Employee(6001,"jerry","password","Jerry Wang","98765432", "admin@nus.com",2000,2,0.5, true));
      employeeRepository.save(new Employee(6002,"cherwah","password","Cher Wah","96543211","staff02@nus.com",1000,1,1.5,true));
      employeeRepository.save(new Employee(6003,"tin","password", "Nguyen Tri Tin", "93456785","staff03@nus.com",1000,1,2.5,true));
      employeeRepository.save(new Employee(6004,"esther", "password", "Esther Tan", "93456445","manager@nus.com",3000,3,3.5,true));
      employeeRepository.save(new Employee(6005,"yuenkwan","password", "Yuen Kwan", "93422445","staff01@nus.com",1000,1,4.5,true));
      
      // Add a Leave Types
      leaveTypeRepository.save(new LeaveType(996,"al",1000,14,0.5,"Annual Leave",true));
      leaveTypeRepository.save(new LeaveType(998,"al_manager",3000,18,0.5,"Annual Leave",true));
      leaveTypeRepository.save(new LeaveType(997,"al_senior",2000,16,0.5,"Annual Leave",true));
      leaveTypeRepository.save(new LeaveType(995,"ml",1000,60,1,"Medical Leave",true));
      leaveTypeRepository.save(new LeaveType(994,"ml_senior",2000,70,1,"Medical Leave",true));
      leaveTypeRepository.save(new LeaveType(993,"ml_manager",3000,80,1,"Medical Leave",true));
      leaveTypeRepository.save(new LeaveType(995,"cl",1000,2,1,"Compensation Leave",true));
      leaveTypeRepository.save(new LeaveType(994,"cl_senior",3,70,1,"Compensation Leave",true));
      leaveTypeRepository.save(new LeaveType(993,"cl_manager",4,80,1,"Compensation Leave",true)); 
    };
	}
}

