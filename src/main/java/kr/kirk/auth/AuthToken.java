package kr.kirk.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthToken {

	private String username;
	private Collection<GrantedAuthority> authorities;
	private String token;
	
	
	public AuthToken(String username, Collection<GrantedAuthority> authorities, String token) {
		this.username = username;
		this.authorities = authorities;
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthToken [username=" + username + ", authorities="
				+ authorities + ", token=" + token + "]";
	}

	
}
