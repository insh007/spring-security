package com.in28minutes.learnspringsecurity.jwt;


import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class JwtAuthenticationResource {

	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication authentication) {
		return new JwtResponse(createToken(authentication)); //~> createToken method
	}
	
	private String createToken(Authentication authentication) {
			var claims = JwtClaimsSet.builder()
									 .issuer("self")
									 .issuedAt(Instant.now())
									 .expiresAt(Instant.now().plusSeconds(60 * 30))
									 .subject(authentication.getName())
									 .claim("scope", createScope(authentication)) // ~> createScope method
									 .build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
	private String createScope(Authentication authentication) {
		// It will map each role then collect and separate by space (Functional Programming) 
		return authentication.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.joining(" "));
	}
	
	@GetMapping("/test-me")
	public String test() {
		return "It's working...";
	}
}

record JwtResponse(String token) {}
