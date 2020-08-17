package com.cap.spring.boot.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cap.spring.boot.entities.JwtRequest;
import com.cap.spring.boot.entities.JwtResponse;
import com.cap.spring.boot.security.JwtTokenUtil;
import com.cap.spring.boot.services.JwtUserDetailsService;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowedHeaders = "*",maxAge = 3600)
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception 
	{
		
		authenticationRequest.getPassword();
		final UserDetails userDetails = 
		 userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
			
			
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println(new JwtResponse(token));
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<?> saveUser(@RequestBody UserDetails user) throws Exception {
//        return ResponseEntity.ok(userDetailsService.save(user));
//    }
	
	@SuppressWarnings("unused")
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
