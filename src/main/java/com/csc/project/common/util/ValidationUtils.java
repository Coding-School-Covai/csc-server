package com.csc.project.common.util;

import java.time.LocalDate;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csc.project.common.exception.UnauthorizedException;
import com.csc.project.common.exception.ValidationException;
import com.csc.project.security.JwtService;

@Component
public class ValidationUtils {

	private final JwtService jwtService;

	// Constructor injection
	public ValidationUtils(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	public String tokenValidate(String token) {
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));

		String[] list = payload.split(",");
		String[] data = list[0].split(":");

		String email = data[1].replace("\"", "");

		String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
		if (chunks.length < 2) {
			throw new ValidationException("Authorization", "Invalid token format");
		}
		if (!jwtService.validateToken(jwt, email)) {
			throw new UnauthorizedException("Invalid or expired token");
		}
		if (email == null || email.isEmpty()) {
			throw new ValidationException("Authorization", "Email not found in token");
		}
		return email;
	}
}
