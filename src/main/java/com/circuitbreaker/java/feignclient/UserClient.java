package com.circuitbreaker.java.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient("USERSERVICE")
public interface UserClient {
	
	@CircuitBreaker(name = "usercircuitbreaker" , fallbackMethod = "someMethod")
	@GetMapping("/users")
	List<String> getUsers();

	public default List<String> someMethod(Exception ex) {
		return List.of("from", "fallback", "method");
	}
}
