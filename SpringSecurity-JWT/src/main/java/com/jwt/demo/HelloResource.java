package com.jwt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.service.MyUserDetailsService;

@RestController
public class HelloResource
{
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@RequestMapping({"/hello"})
	public String home()
	{
		return ("welocome alien");
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?>createAuthenticationToken
						(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e)
		{
		throw new Exception("incorrect uname and pass",e);
		}
		
		final UserDetails userDetails=myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt =jwtTokenUtil.generateToken(userDetails);
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
				
	}

}
