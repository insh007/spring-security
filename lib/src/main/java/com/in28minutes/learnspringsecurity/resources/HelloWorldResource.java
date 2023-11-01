package com.in28minutes.learnspringsecurity.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@GetMapping
	public String helloWorld() {
		return "HelloWorld";
	}
}

/*Note: By default in spring security everything is authenticated means when you
 * hit the above Rest API on localhost:8080 then you won't get directly HelloWorld
 * on browser instead of this you will find a Login Page which is by default
 * authentication provide by spring security
 * */
 