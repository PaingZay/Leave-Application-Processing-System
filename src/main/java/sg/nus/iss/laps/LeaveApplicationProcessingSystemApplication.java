package sg.nus.iss.laps;

import java.time.LocalDate;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.nus.iss.laps.model.Application;
import sg.nus.iss.laps.model.ApplicationStatus;
import sg.nus.iss.laps.model.Employee;
import sg.nus.iss.laps.repository.ApplicationRepository;
import sg.nus.iss.laps.repository.EmployeeRepository;


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
	  CommandLineRunner initData(ApplicationRepository applicationRepository, EmployeeRepository employeeRepository) 
	  {
	    return (args) -> {
	    	
	      Employee pz = employeeRepository.saveAndFlush(new Employee("Paing", "123456", "Paing Zay", "PZ@gmail.com", 00001, 1, 20.0));
	      Employee dio = employeeRepository.saveAndFlush(new Employee("Dio", "123457", "Dio Brando", "dio@gmail.com", 00002, 2, 20.0));
	      Employee jotaro = employeeRepository.saveAndFlush(new Employee("Jotaro", "123458", "Kujo Jotaro", "jojo@gmail.com", 00003, 2, 30));
	      
	      Application app1=new Application();
	      app1.setEmployee(pz);
	      app1.setApplicationDate(LocalDate.now());
	      app1.setLeaveStartDate(LocalDate.of(2023,9,11));
	      app1.setLeaveEndDate(LocalDate.of(2023,9,13));
	      app1.setLeaveType("Medical");
	      app1.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app1);
	      
	      Application app2=new Application();
	      app2.setEmployee(dio);
	      app2.setApplicationDate(LocalDate.now());
	      app2.setLeaveStartDate(LocalDate.of(2023,9,12));
	      app2.setLeaveEndDate(LocalDate.of(2023,9,13));
	      app2.setLeaveType("Compensation");
	      app2.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app2);
	      
	      Application app3=new Application();
	      app3.setEmployee(jotaro);
	      app3.setApplicationDate(LocalDate.now());
	      app3.setLeaveStartDate(LocalDate.of(2023,9,15));
	      app3.setLeaveEndDate(LocalDate.of(2023,9,16));
	      app3.setLeaveType("Annual");
	      app3.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app3);

//	      Application app4=new Application();
//	      app4.setEmployee(jotaro);
//	      app4.setStatus(ApplicationStatus.APPLIED);
//	      applicationRepository.saveAndFlush(app4);
	    };
	  }

}
