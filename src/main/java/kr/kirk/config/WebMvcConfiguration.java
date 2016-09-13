package kr.kirk.config;

import kr.kirk.auth.AuthTokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
/*
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
*/
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authTokenInterceptor())
				.addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/login");
	}

	@Bean
	public HandlerInterceptor authTokenInterceptor() {
		return new AuthTokenInterceptor();
	}
}
