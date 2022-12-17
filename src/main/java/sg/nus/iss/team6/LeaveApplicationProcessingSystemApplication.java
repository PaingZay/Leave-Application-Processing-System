package sg.nus.iss.team6;

import java.time.LocalDate;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.team6.model.LeaveApplication;
import sg.nus.iss.team6.model.ApplicationStatus;
import sg.nus.iss.team6.model.Employee;
import sg.nus.iss.team6.repository.LeaveApplicationRepository;
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
	  CommandLineRunner initData(LeaveApplicationRepository applicationRepository, EmployeeRepository employeeRepository) 
	  {
	    return (args) -> {
	    	
	      Employee pz = employeeRepository.saveAndFlush(new Employee("Paing", "123456", "Paing Zay", "PZ@gmail.com", 1, 1, 20.0));
	      Employee dio = employeeRepository.saveAndFlush(new Employee("Dio", "123457", "Dio Brando", "dio@gmail.com", 2, 2, 20.0));
	      Employee jotaro = employeeRepository.saveAndFlush(new Employee("Jotaro", "123458", "Kujo Jotaro", "jojo@gmail.com", 3, 2, 30));
	      
	      LeaveApplication app1=new LeaveApplication();
	      app1.setEmployee(pz);
	      app1.setApplicationDate(LocalDate.now());
	      app1.setLeaveStartDate(LocalDate.of(2023,9,11));
	      app1.setLeaveEndDate(LocalDate.of(2023,9,13));
	      app1.setLeaveType("Medical");
	      app1.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app1);
	      
	      LeaveApplication app2=new LeaveApplication();
	      app2.setEmployee(dio);
	      app2.setApplicationDate(LocalDate.now());
	      app2.setLeaveStartDate(LocalDate.of(2023,9,12));
	      app2.setLeaveEndDate(LocalDate.of(2023,9,13));
	      app2.setLeaveType("Compensation");
	      app2.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app2);
	      
	      LeaveApplication app3=new LeaveApplication();
	      app3.setEmployee(jotaro);
	      app3.setApplicationDate(LocalDate.now());
	      app3.setLeaveStartDate(LocalDate.of(2023,9,15));
	      app3.setLeaveEndDate(LocalDate.of(2023,9,16));
	      app3.setLeaveType("Annual");
	      app3.setStatus(ApplicationStatus.APPLIED);
	      applicationRepository.saveAndFlush(app3);
	    };
	  }

}
