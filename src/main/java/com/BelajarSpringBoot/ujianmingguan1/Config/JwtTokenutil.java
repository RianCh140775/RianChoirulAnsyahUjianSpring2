package com.BelajarSpringBoot.ujianmingguan1.Config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenutil implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6491936126884985732L;


	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	
	@Value("${jwt.secret}")
	private String secret;
	
	// jwtTokenutil ini reusable bisa dipake di project lain dengan mengcopas ke project lain saja

	// menerima username jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject); // user meneruma subject jwt
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
		final Claims claims = getAllClaimsFromToken(token); // ini data semua class
		return claimsResolver.apply(claims); // lalu di apply berdasarkan subject
	}
	// yang di atas khusus mendapatkan subject
	
	
	// menerima name dari token
	public String getNameFromToken(String token) {
		return getAllClaimsFromToken(token).get("name").toString();
	}
	
	// untuk mendapatlam informasi dari token dengan secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	// mendapatkan expired date dari jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	//ngecek token kadaluarsa
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	// Generate Token untuk user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims =  new HashMap<String, Object>();
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY *1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		return token;
	}
	
	// validasi token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equalsIgnoreCase(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
}
