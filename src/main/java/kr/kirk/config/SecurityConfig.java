package kr.kirk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http
         .authorizeRequests()
             .antMatchers("/", "/favicon.ico", "/resources/**").permitAll()
             .antMatchers("/admin/login").anonymous()
             .antMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/admin/login")
             	.loginProcessingUrl("/admin_login_check")
//             .usernameParameter("j_username")
//             .passwordParameter("j_password")
//             .successHandler(loginSuccessHandler)
             	.defaultSuccessUrl("/admin/login_ok")
             .permitAll()
             .and()
         .logout()
             .logoutUrl("/logout")
             .logoutSuccessUrl("/")
             .and()
         .csrf().disable()
         .httpBasic();
	}
	
	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication().withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				/*
				.and()
				.withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				*/
	}
}
