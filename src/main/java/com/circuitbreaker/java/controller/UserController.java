package com.circuitbreaker.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.circuitbreaker.java.service.UserService;

@RestController
public class UserController {

    @Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<String> getUsers() {
		return userService.getUsers();
	}
}
