package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// authorizing http request
		http.authorizeHttpRequests(auth -> {
				auth.anyRequest().authenticated();
			});
		
		// Disabling sessions by making them STATELESS
		http.sessionManagement(
					session -> 
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				);
		
		
		//http.formLogin();
		http.httpBasic();
		
		// Disabling CSRF
		http.csrf().disable();
		
		return http.build();	
		}
}

/*
Note : 
Need Change SpringBootWebSecurityConfiguration which is used by default by spring security
to provide default authentication(can see in first commit) so that we do not need 
CSRF token to perform Post or Put request means we converting the request 
into "STATELESS"
*/