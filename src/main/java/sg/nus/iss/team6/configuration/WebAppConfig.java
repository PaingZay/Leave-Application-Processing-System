package sg.nus.iss.team6.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//remember to import interceptor

@Component
public class WebAppConfig implements WebMvcConfigurer{
//  @Autowired
//  SecurityInterceptor securityInterceptor;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(securityInterceptor);
  }
}
