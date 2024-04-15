package com.demo.springbootsecurity.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.springbootsecurity.util.JwtTokenUtil;

public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService ; 
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil ;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get the authorization header from the inbound request
		final String requestTokenHeader= request.getHeader("Authorization") ;
		
		String username = null ;
		String jewToken=null ;
		
		//extract the Bearer header  and the token if the authorization head is not null
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
		{
			// the token always lies between[ 7: ] , Bearer holds space
			jewToken = requestTokenHeader.substring(7) ;
			username = jwtTokenUtil.getUsernameFromToken(jewToken) ;
		}
		
		//check for the user name and securitycontext
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(username) ;
			
			//validate the username from the token
			if(jwtTokenUtil.validateToken(jewToken, userDetails)) 
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails	, null, userDetails.getAuthorities()) ;
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
		}
		
		filterChain.doFilter(request, response); //always filter
	}

}
