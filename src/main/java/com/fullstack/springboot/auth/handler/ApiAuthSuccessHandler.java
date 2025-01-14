package com.fullstack.springboot.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fullstack.springboot.dto.EmployeesAuthDTO;
import com.fullstack.springboot.util.JWTUtil;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiAuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("auth-success-handler");
		System.out.println(authentication);
		
		EmployeesAuthDTO dto = (EmployeesAuthDTO)authentication.getPrincipal();
		Map<String, Object> claims = dto.getClaims();
		
		String accessToken = JWTUtil.genToken(claims, 60);
		String validateToken = JWTUtil.genToken(claims, 60*12);

		claims.put("accessToken", accessToken);
		claims.put("refreshToken", validateToken);
		
		System.out.println("Tokens");
		System.out.println(accessToken);
		System.out.println(validateToken);
		System.out.println("Tokens");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(claims);
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.close();
		
		
	}

}
