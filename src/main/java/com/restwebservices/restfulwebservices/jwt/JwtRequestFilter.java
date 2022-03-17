package com.restwebservices.restfulwebservices.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.restwebservices.restfulwebservices.securiy.MyUserDetailService;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter{

	@Autowired
	MyUserDetailService detailService;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader=request.getHeader("Authorization");

		String username=" ",jwt="";
		if(null!=authHeader&&authHeader.startsWith("Bearer")) {
			jwt=authHeader.toString();
			username=jwtUtil.extractUsername(jwt);
		}
		if(null!=username&& SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails details=this.detailService.loadUserByUsername(username);
			if(jwtUtil.validatetoken(jwt, details)) {
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(details, null,details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

			filterChain.doFilter(request, response);
		}



	}

}
