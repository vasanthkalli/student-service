package com.student.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.student.util.JWTUtils;
@Component
public class JWTFilter extends OncePerRequestFilter{
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsServicel;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger logger=LoggerFactory.getLogger(JWTFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader=request.getHeader("Authorization");
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			try {
			String JwtToken=authorizationHeader.substring(7);
			if(JwtToken!=null && jwtUtils.validateToken(JwtToken)) {
				String userName=jwtUtils.getUserNameFromToken(JwtToken);
				UserDetails  user=userDetailsServicel.loadUserByUsername(userName);
				Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}catch(Exception e) {
			logger.error("could not set AuthToken");
		}
		}
		
		filterChain.doFilter(request, response);
	}

}
