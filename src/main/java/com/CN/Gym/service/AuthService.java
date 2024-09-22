package com.CN.Gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.CN.Gym.dto.JwtRequest;
import com.CN.Gym.dto.JwtResponse;
import com.CN.Gym.jwt.JwtAuthenticationHelper;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtAuthenticationHelper authenticationHelper;

	public JwtResponse login(JwtRequest jwtRequest) {
		Authentication authentication = this.doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		String token = authenticationHelper.generateToken((UserDetails)authentication.getPrincipal());
		JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token).build();
		return jwtResponse;
	}

	private Authentication doAuthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		}
		catch(BadCredentialsException e){
			throw new BadCredentialsException("Invalid Username or Password");
		}
	}

}
