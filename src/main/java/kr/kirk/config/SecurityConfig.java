package kr.kirk.config;

import kr.kirk.auth.MultipleHttpSessionStrategy;
import kr.kirk.auth.RestAuthEntryPoint;
import kr.kirk.auth.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig {

	@Autowired
	UserService userService;
	
	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				/*
				.and()
				.withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				*/
//		auth.userDetailsService(userService);
	}

	@Configuration
	@Order(1)
	public static class ApiSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private RestAuthEntryPoint restAuthEntryPoint;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
	
			CharacterEncodingFilter filter = new CharacterEncodingFilter();
			filter.setEncoding("UTF-8");
			filter.setForceEncoding(true);
			http.addFilterBefore(filter, CsrfFilter.class);
			
			http
				.csrf().disable()
				.antMatcher("/api/**")
				.authorizeRequests()
	            	.antMatchers("/api/login").anonymous()
	            	.antMatchers("/api/**").hasRole("ADMIN")
	            .and()
	            .exceptionHandling()
				.authenticationEntryPoint(restAuthEntryPoint)
				.and()
	         .httpBasic().disable();
		}
	}
	
	@Configuration
	public static class WebAdminSecurityConfigAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/", "/favicon.ico", "/resources/**");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			CharacterEncodingFilter filter = new CharacterEncodingFilter();
			filter.setEncoding("UTF-8");
			filter.setForceEncoding(true);
			http.addFilterBefore(filter, CsrfFilter.class);
			
			http
				.csrf().disable()
				.authorizeRequests()
	            	.antMatchers("/admin/login").anonymous()
	            	.antMatchers("/admin/**").hasRole("ADMIN")
	            	.anyRequest().authenticated()
	            	.and()
	            .formLogin()
	            	.loginPage("/admin/login")
	             	.loginProcessingUrl("/admin/login_processing")
	             	.usernameParameter("j_username")
	             	.passwordParameter("j_password")
	             	.successForwardUrl("/admin/home")
//	             	.successHandler(saveRequestAwareAuthSuccessHandler())
	             	.failureUrl("/admin/login?error")
//	             	.failureHandler(authenticationFailureHandler())
	             	.permitAll()
	             	.and()
	            .logout()
	            	.logoutUrl("/logout")
	            	.logoutSuccessUrl("/admin/login?logout")
	            	.and()
	            .httpBasic().disable();
		}
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy(ContentNegotiationStrategy c) {
		return new MultipleHttpSessionStrategy(c);
	}	
}
