package sg.nus.iss.team6.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SecurityInterceptor implements HandlerInterceptor{
	
			@Override
			public boolean preHandle(HttpServletRequest request, 
						HttpServletResponse response, Object handler) throws IOException 
			{
				System.out.println("preHandle");
				HttpSession session = request.getSession();
				String username = (String) session.getAttribute("username");
			    if (username == null) {
			      //Not Login
			      response.sendRedirect("/login");
			      return false;
			    }
			    
				return true; 
			}
			
			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse
			response, Object handler, ModelAndView modelAndView) {
			
			}
			
			@Override
			public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, Object handler, Exception ex) {
			
			}
}
