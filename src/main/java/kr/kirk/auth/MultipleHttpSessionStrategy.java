package kr.kirk.auth;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ContentNegotiationStrategy;

@Component
public class MultipleHttpSessionStrategy implements HttpSessionStrategy {
	
	private HttpSessionStrategy api;
	private HttpSessionStrategy web;
	private RequestMatcher requestMatcher;

	
	@Autowired
	public MultipleHttpSessionStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
		this(new CookieHttpSessionStrategy(), new HeaderHttpSessionStrategy());
		
		MediaTypeRequestMatcher matcher = new MediaTypeRequestMatcher( contentNegotiationStrategy, Arrays.asList(MediaType.TEXT_HTML));
		matcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
		
		RequestHeaderRequestMatcher javascript = new RequestHeaderRequestMatcher("X-Requested-With");
		
		requestMatcher = new OrRequestMatcher(Arrays.asList(matcher, javascript));
	}

	
	public MultipleHttpSessionStrategy( CookieHttpSessionStrategy cookieHttpSessionStrategy, HeaderHttpSessionStrategy headerHttpSessionStrategy) {
		web = cookieHttpSessionStrategy;
		api = headerHttpSessionStrategy;
	}

	private HttpSessionStrategy getHttpStrategy(HttpServletRequest request) {
		return requestMatcher.matches(request) ? web : api;
	}

	@Override
	public String getRequestedSessionId(HttpServletRequest request) {
		return getHttpStrategy(request).getRequestedSessionId(request);
	}

	@Override
	public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
		getHttpStrategy(request).onInvalidateSession(request, response);	}

	@Override
	public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
		getHttpStrategy(request).onNewSession(session, request, response);
	}

}
