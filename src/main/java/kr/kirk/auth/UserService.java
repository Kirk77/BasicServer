package kr.kirk.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	public Collection<GrantedAuthority> getAuthorities(String username);
}
