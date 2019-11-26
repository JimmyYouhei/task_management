package com.hvcg.api.task_management.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;



public class JwtTokenFilter extends GenericFilterBean {

	private static final String BEARER = "Bearer";
	
	private UserDetailService userDetailService;
	
	public JwtTokenFilter(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String headerValue = ((HttpServletRequest)request).getHeader("Authorization");
		
	
		getBearToken(headerValue).ifPresent(token -> {
			userDetailService.loadUserByJwtToken(token).ifPresent(userDetails -> {
				SecurityContextHolder.getContext()
				.setAuthentication(new PreAuthenticatedAuthenticationToken(userDetails, "" , userDetails.getAuthorities()));
			});
		});
	
		chain.doFilter(request, response);
	}
	
	private Optional<String> getBearToken(String headerValue){
		if(headerValue != null && headerValue.startsWith(BEARER)) {
			return Optional.of(headerValue.replace(BEARER , "").trim());
		}
		
		return Optional.empty();
	}
	
	
}
