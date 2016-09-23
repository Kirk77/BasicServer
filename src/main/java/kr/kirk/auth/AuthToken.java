package kr.kirk.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class AuthToken {

	private String username;
	private List<GrantedAuthority> authorities;
	private String token;
	
	public AuthToken() {}
	
	public AuthToken(String username, List<GrantedAuthority> authorities, String token) {
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
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<GrantedAuthority> authorities) {
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
