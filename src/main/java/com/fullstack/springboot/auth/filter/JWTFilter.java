
package com.fullstack.springboot.auth.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fullstack.springboot.dto.EmployeesAuthDTO;
import com.fullstack.springboot.util.JWTUtil;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		System.out.println("JWT-filter-notFilter");
		
		String path = request.getRequestURI();
		System.out.println("path = " + path);
		if(path.startsWith("/auth")) {
			return true;
		}
		if(path.startsWith("/chat")) {
			return true;
		}
		if(path.startsWith("/api/empImage/view")) {
			return true;
		}
		
		
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("JWT-filter-internal");
		
		try {
			String authHeader = request.getHeader("Authorization").substring(7);
			System.out.println(authHeader);
			Map<String, Object> claims = JWTUtil.validateToken(authHeader);
			String email = (String)(claims.get("email"));
			String password = (String)(claims.get("password"));
			Long empNo = Long.valueOf((int)claims.get("empNo")) ;
			Long deptNo = Long.valueOf((int)claims.get("deptNo")) ;
			Set<String> roleSet = new HashSet<String>((ArrayList) claims.get("roleSet"));
			
			EmployeesAuthDTO dto = new EmployeesAuthDTO(email, password, empNo, deptNo, roleSet);
			UsernamePasswordAuthenticationToken authToken =
					new UsernamePasswordAuthenticationToken(dto, password, dto.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("JWT token error");
			System.out.println(e);
			Gson gson = new Gson();
			String msg = gson.toJson(Map.of("error","ERROR_TOKEN_ACCESS"));
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.close();
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}

}

// package com.fullstack.springboot.auth.filter;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.fullstack.springboot.dto.EmployeesAuthDTO;
// import com.fullstack.springboot.util.JWTUtil;
// import com.google.gson.Gson;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class JWTFilter extends OncePerRequestFilter {
	
// 	@Override
// 	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
// 		System.out.println("JWT-filter-notFilter");
		
// 		String path = request.getRequestURI();
// 		System.out.println("path = " + path);
// 		if(path.startsWith("/auth")) {
// 			return true;
// 		}
		
// 		return false;
// 	}
	
// 	@Override
// 	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
// 			throws ServletException, IOException {
// 		System.out.println("JWT-filter-internal");
		
// 		try {
// 			String authHeader = request.getHeader("Authorization").substring(7);
// 			System.out.println(authHeader);
// 			Map<String, Object> claims = JWTUtil.validateToken(authHeader);
// 			String email = (String)(claims.get("email"));
// 			String password = (String)(claims.get("password"));
// 			Set<String> roleSet = new HashSet<String>((ArrayList) claims.get("roleSet"));
			
// 			EmployeesAuthDTO dto = new EmployeesAuthDTO(email, password, roleSet);
// 			UsernamePasswordAuthenticationToken authToken =
// 					new UsernamePasswordAuthenticationToken(dto, password, dto.getAuthorities());
// 			SecurityContextHolder.getContext().setAuthentication(authToken);
			
// 		} catch (Exception e) {
// 			// TODO: handle exception
// 			System.out.println("JWT token error");

// 			Gson gson = new Gson();
// 			String msg = gson.toJson(Map.of("error","ERROR_TOKEN_ACCESS"));
			
// 			response.setContentType("application/json");
// 			PrintWriter out = response.getWriter();
// 			out.println(msg);
// 			out.close();
// 			return;
// 		}
		
// 		filterChain.doFilter(request, response);
		
// 	}

// }
