package com.demo.springbootsecurity.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// retrieve username from jwt token
	/**
	 * 
	 * This method accepts the jwt as input and 
	 * 	returns the claim
	 * @param String jwtToken
	 * 
	 * @return String claim
	 * 
	 * */
	public String getUsernameFromToken(final String token) {
		//subject was set as the username in the token
		return getClaimFromToken(token, Claims::getSubject);

	}

	public <T> T getClaimFromToken(final String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// to retrieve any information from token the secret key is required
	private Claims getAllClaimsFromToken(final String token) {
		
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Check if the token has expired
	/**
	 * Method to check the token expiry and checks if expired or not
	 * 
	 * @param String jwt
	 * @return Boolean isTokenExpired
	 * 
	 * */
	private Boolean isTokenExpired(final String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}

	// retrive expiration date from the token
	public Date getExpirationDateFromToken(final String token) {
		
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	//overloaded method
	 public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	       return doGenerateToken(claims, username) ;
	    }
	 
	/**
	 * 
	 * This method generates the JWT setting the subject and claims
	 * 
	 * @param Map<String, Object> claims
	 * @param String subject
	 * 
	 * @return JSON WEB TOKEN
	 * 
	 * */
	private String doGenerateToken(final Map<String, Object> claims, final String subject) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate token
	public Boolean validateToken(String tokenString , UserDetails userDetails)
	{
		final String userNameString=getUsernameFromToken(tokenString) ;
		return (userNameString.equals(userDetails.getUsername()) && !isTokenExpired(tokenString)) ;
	}

}
