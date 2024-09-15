package com.user.registration.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.user.registration.securityconfig.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
	    String token = null;
	    String username = null;
	    
	    if(header != null && header.startsWith("Bearer ")) {
	        token = header.substring(7);
	        username = jwtService.extractUsername(token);
	    }
	    
	    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
	        if(jwtService.validateToken(token, userDetails)) {
	            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            userToken.setDetails(new WebAuthenticationDetails(request));
	            SecurityContextHolder.getContext().setAuthentication(userToken);
	        }
	    }
	    
	    filterChain.doFilter(request, response); // No return statement needed here
	}
}