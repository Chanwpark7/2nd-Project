package com.fullstack.springboot.util;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
	private static String key = "SECRET_KEY_FOR_JWT_ENCRYPT_GENERATE_PRACTICE";
	

	public static String genToken(Map<String, Object> valueMap, int min) {
		SecretKey key = null;
		
		try {
			key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		String jwtStr2 = Jwts.builder().signWith(key)
						.header().add(Map.of("typ","JWT")).and()
						.claims().add(valueMap).and()
						.issuedAt(Date.from(ZonedDateTime.now().toInstant()))
						.expiration(Date.from(ZonedDateTime.now().plusMinutes(Long.valueOf(min)).toInstant()))
						.compact();
		return jwtStr2;
	}

	public static Map<String, Object> validateToken(String token){
		Map<String, Object> claim = null;
		SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes());
		try {
			//claim = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
			System.out.println(Jwts.parser().verifyWith(key).build().parseSignedClaims(token));
			claim = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("validate:"+e);
		}
		
		return claim;
		
	}
	
	

}
