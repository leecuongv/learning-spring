package com.cuonglv.learning_spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	public String secret;
	public final Key key;

	public JwtUtil() {
		// Tạo khóa bí mật sử dụng thuật toán HS256
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	public String extractUsername(String token) throws Exception {
		return extractAllClaims(token).getSubject();
	}

	public Date extractExpiration(String token) throws Exception {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) throws Exception {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) throws Exception {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) throws Exception {

		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 giờ
				.signWith(key).compact();

	}

	public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
