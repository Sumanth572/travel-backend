package com.travelManagement.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component

public class JwtUtil {
	private final String SECRET_KEY="mysecretkey";
	private final long EXPIRATION_TIME=1000*60*60;
	
	public String generateToken(String email)
	{
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256,SECRET_KEY)
				.compact();
	}
	public String extractEmail(String token)
	{
		return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
	}

	 public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            return false;
	        }
	        
	 }
}
