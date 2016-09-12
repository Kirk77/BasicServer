package kr.kirk.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthTokenInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(AuthTokenInterceptor.class);
	private final String XAUTHTOKEN = "X-AUTH-TOKEN";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String xAuthToken = request.getHeader(XAUTHTOKEN);
		if ( xAuthToken == null || xAuthToken.isEmpty() ) {
	
		} else {
			logger.info("{} : {}", XAUTHTOKEN, xAuthToken);
			response.setHeader(XAUTHTOKEN, xAuthToken);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		// TODO Auto-generated method stub
		
	}
	
}
