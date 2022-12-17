package sg.nus.iss.laps.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.nus.iss.laps.model.Application;

@Service
public interface ApplicationService
{
	List<Application> findAllApplication();
//	
	public void addBooking(Application application);
	
	Application findApplication(Integer id);
	
	Application changeApplication(Application application);

}
