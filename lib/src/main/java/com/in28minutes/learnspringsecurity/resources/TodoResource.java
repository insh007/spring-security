package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final List<Todo> Todos_list = 
			List.of(new Todo("inshad", "spring-security"),
					new Todo("Ranga", "AWS"),
					new Todo("in28minutes", "AWS"));
	
	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return Todos_list;
	}
	
	@GetMapping("users/{username}/todos")
	public Todo retrieveTodosForSpecificUser(@PathVariable String username) {
		return Todos_list.get(0);
	}
	
	//Note: when we use spring security then we need CSRF (Cross-site request forgery) 
	// token to perform a Post and Put request
	@PostMapping("users/{username}/todos")
	public void createTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}
}

record Todo (String username, String description) {}