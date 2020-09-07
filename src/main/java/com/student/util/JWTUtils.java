package com.student.util;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import com.student.dto.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
@Component
public class JWTUtils {
	
	private static final Logger logger=LoggerFactory.getLogger(JWTUtils.class);

	private static final int JWT_TOKEN_VALIDITY = 365*24*60*60;
	
	@Value("${secret}")
	private String secret;

	public  String generateToken(Authentication authentication) {
		
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			logger.error("Invalid JWT Signature {}",e.getMessage());
		}catch (ExpiredJwtException e) {
			logger.error("JWT Token Expired {}",e.getMessage());
		}catch(Exception e) {
			logger.error("Exception while validating token {}",e.getMessage());
		}
		return false;
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
}
