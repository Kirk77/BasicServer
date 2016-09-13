package kr.kirk.config;

import kr.kirk.auth.RestAuthEntryPoint;
import kr.kirk.auth.SaveRequestAwareAuthSuccessHandler;
import kr.kirk.auth.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
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
			.exceptionHandling()
			.authenticationEntryPoint(restAuthEntryPoint)
				.and()
			.authorizeRequests()
             .antMatchers("/", "/favicon.ico", "/resources/**").permitAll()
             .antMatchers("/admin/login", "/api/login").anonymous()
             .antMatchers("/admin/**", "/api/**").hasRole("ADMIN")
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/admin/login")
             	.loginProcessingUrl("/admin_login_check")
             	.usernameParameter("j_username")
             	.passwordParameter("j_password")
             	.defaultSuccessUrl("/admin/home")
             	.successHandler(saveRequestAwareAuthSuccessHandler())
             	.failureHandler(authenticationFailureHandler())
             .permitAll()
             .and()
         .logout()
             .logoutUrl("/logout")
             .logoutSuccessUrl("/admin/login?logout")
             .and()
         
         .httpBasic();
	}
	
	@Value("${management.shell.auth.simple.user.name}")
	private String remoteShellAdminID;
	@Value("${management.shell.auth.simple.user.password}")
	private String remoteShellAdminPassword;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				/*
				.and()
				.withUser(remoteShellAdminID).password(remoteShellAdminPassword).roles("ADMIN");
				*/
//		auth.userDetailsService(userService);
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

	@Bean
	public SaveRequestAwareAuthSuccessHandler saveRequestAwareAuthSuccessHandler() {
		return new SaveRequestAwareAuthSuccessHandler();
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
