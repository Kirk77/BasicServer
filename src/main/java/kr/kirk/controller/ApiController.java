package kr.kirk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.kirk.auth.AuthRequest;
import kr.kirk.auth.AuthToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	AuthenticationManager authManager;
	
	
	@RequestMapping(value = "/api/ping", method=RequestMethod.POST)
	@ResponseBody
	public String ping() {
		logger.info("api ping");
		return "pong";
	}
	
	@RequestMapping(value="/api/login", method=RequestMethod.POST)
	public AuthToken login( @RequestBody AuthRequest authRequest, HttpSession session) {
		
		String username = authRequest.getUsername();
		String password = authRequest.getPassword();
				
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication auth = authManager.authenticate(token);
		SecurityContext sctx = SecurityContextHolder.getContext();
		sctx.setAuthentication(auth);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sctx);
		
		//
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        
        AuthToken authToken = new AuthToken(username, authorities, session.getId());
        logger.info("api login... password : {}\\n{}", password, authToken);
		return authToken;
		
	}
}
