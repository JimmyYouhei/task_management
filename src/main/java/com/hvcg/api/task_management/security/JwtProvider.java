package com.hvcg.api.task_management.security;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtProvider {

	private final String ROLES_KEY = "role";
	
	
	private String secretKey;
	private long validityInMiliseconds;
	
	// dependency injection
	@Autowired
	public JwtProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
						@Value("${security.jwt.token.expiration}")long validityInMillisecond) {
		
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.validityInMiliseconds = validityInMillisecond;
		
	}
	
	
	public String createToken(String username , String role) {
		
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(ROLES_KEY , "ROLE_" + role );
		
		Date now = new Date();
		Date exDate = new Date(now.getTime() + validityInMiliseconds);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exDate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.setHeaderParam("typ" , "JWT")
				.compact();
	}
	
	
	public boolean isValidToken(String token) {
			try {
				Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
				return true;
			} catch (JwtException | IllegalArgumentException e) {
				return false;
			}
		}
	
		public String getUsername(String token) {
			return Jwts.parser().setSigningKey(secretKey)
					.parseClaimsJws(token).getBody().getSubject();
		}
	
		
		public String getRoles(String token) {
			
			String roleClaims = Jwts.parser().setSigningKey(secretKey)
					.parseClaimsJws(token).getBody().get(ROLES_KEY , String.class);
			
			return roleClaims;
			
		}
}
