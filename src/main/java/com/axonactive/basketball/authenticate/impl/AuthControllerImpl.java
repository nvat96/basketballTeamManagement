package com.axonactive.basketball.authenticate.impl;


import com.axonactive.basketball.authenticate.AuthController;
import com.axonactive.basketball.security.jwt.JwtRequest;
import com.axonactive.basketball.security.jwt.JwtResponse;
import com.axonactive.basketball.security.jwt.JwtUtils;
import com.axonactive.basketball.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthControllerImpl implements AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;

	public ResponseEntity<?> authenticateUser(JwtRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getUsername(),
												 roles));
	}
}
