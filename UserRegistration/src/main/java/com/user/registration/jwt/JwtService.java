package com.user.registration.jwt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String secretkey ="";
	
	public JwtService() {
		try {
			KeyGenerator key = KeyGenerator.getInstance("hmacSHA256");
			SecretKey sk = key.generateKey();
			secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	public String  generateToken(String username) {
		Map <String , Object> claims = new HashMap<>();
		
		return Jwts.builder()
				   .claims()
				   .add(claims)
				   .subject(username)
				   .issuedAt(new Date(System.currentTimeMillis()))
				   .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30 * 1000))
				   .and()
				   .signWith(getKey())
				   .compact();
	}

	private Key getKey() {
		byte[] keybytes = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
	
	

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean flag = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
