package kr.kirk.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername : {}", username);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword("admin123");
		user.setAuthorities(getAuthorities(username));
		return user;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		logger.info("getAuthorities : {}", username);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        authorities.add(new SimpleGrantedAuthority("USER"));
        
        return authorities;
	}

}
