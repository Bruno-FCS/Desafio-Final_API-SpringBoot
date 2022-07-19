package com.brunofernandes.apispringboot.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brunofernandes.apispringboot.entities.UserResponse;
import com.brunofernandes.apispringboot.services.exceptions.AuthenticationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private static long JWT_TOKEN_VALIDITY;

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(UserResponse user) {
		return Jwts.builder().claim("user_id", user.getUser_id()).claim("user_name", user.getUser_name())
				.claim("user_email", user.getUser_email()).claim("user_full_name", user.getUser_full_name())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public void verifyToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (RuntimeException e) {
			throw new AuthenticationException();
		}

	}
}
