package com.demo.springbootsecurity.contorllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springbootsecurity.entities.JwtRequest;
import com.demo.springbootsecurity.util.JwtTokenUtil;

@RestController(value = "/api")
public class CustomJwtController {
	
	
	private transient JwtTokenUtil jwtTokenUtil ;
	private AuthenticationManager authenticationManager ;
	
	
	@PostMapping(name = "/login" , consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE , MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String login(@RequestBody final JwtRequest jwtRequest)
	{
		try {
			//try to authenticate first if successful then generate the token
		Authentication authentication =	authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername() , jwtRequest.getPassword())
            );
			
		if(authentication.isAuthenticated())
		{
			generateToken(jwtRequest) ;
		}
			
		} catch (Exception e) {
			
		}
		return "Login" ;
	}
	
	
	private void generateToken(final JwtRequest jwtRequest) throws Exception
	{ 
		
		try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername() , jwtRequest.getPassword())
            );
            
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        String tokenString= jwtTokenUtil.generateToken(jwtRequest.getUsername());
     }
	
	
	
}
