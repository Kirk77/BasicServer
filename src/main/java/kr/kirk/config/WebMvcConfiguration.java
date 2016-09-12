package kr.kirk.config;

import kr.kirk.auth.AuthTokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
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
