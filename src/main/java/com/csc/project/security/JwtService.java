package com.csc.project.security;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	private static final Logger logger = LogManager.getLogger(JwtService.class);

    private static final String SECRET_KEY_BASE64 = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"; // Replace with your constant key

    private static final SecretKey SECRET_KEY = new SecretKeySpec(
            Base64.getDecoder().decode(SECRET_KEY_BASE64), SignatureAlgorithm.HS256.getJcaName());

    public String generateToken(String email) {
        try {
            // Generate the JWT token using the constant secret key
            return Jwts.builder()
                    .setSubject(email) // Payload: subject (email)
                    .setIssuedAt(new Date()) // Token issue time
                    .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                    .signWith(SECRET_KEY) // Sign with the constant secret key
                    .compact(); // Generate the JWT token
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public Boolean validateToken(String token, String email) {
        try {
            logger.info("in validate token-------------: {}", email);
            logger.info("in validate token-------token------: {}", token);

            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // Use the same constant secret key to verify the signature
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException e) {
            return false; // If the token is invalid or the signature doesn't match
        }
    }    
    
   

}
