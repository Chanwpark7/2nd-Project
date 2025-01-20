package com.fullstack.springboot.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TokenController {
	
	@GetMapping("/auth/refresh")
	public Map<String, Object> postMethodName(@RequestParam String refreshToken) {
		//TODO: process POST request

		if(refreshToken == null) {
			throw new RuntimeException("NLL_REFRESH_TOKEN");
		}
		if(refreshToken == null || refreshToken.length() < 7) {
			throw new RuntimeException("Invalid_STRING");
		}
		

		Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
		try {
		log.error("refreshToken claims,,," + claims);
		System.out.println("claims get:" + claims.get("exp"));
		String newAccessToken = JWTUtil.genToken(claims, 60);
		String newRefreshToken = checkTime((long)claims.get("exp")) == true ? 
				JWTUtil.genToken(claims, 60*24) : refreshToken;
		return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
//		
		}catch(Exception e) {
			return Map.of("error","TOKEN_ERR");
		}
		//return null;
		
	}

	private boolean checkTime(long exp) {
		Date expDate = new Date(exp * 1000);
		long gap = expDate.getTime() - System.currentTimeMillis();
		
		long leftMin = gap/(1000*60);
		
		return leftMin < 60;
	}
	
	private boolean checkExpiredToken(String token) {
		try {
			JWTUtil.validateToken(token);
		}catch(Exception e) {
			if(e.getMessage().equals("Expired")) {
				return true;
			}
		}
		return false;
	}
}
