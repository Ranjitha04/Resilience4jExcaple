package com.circuitbreaker.java.service;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


public interface UserService {

	List<String> getUsers();
	
}
