package com.circuitbreaker.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.circuitbreaker.java.feignclient.UserClient;
import com.circuitbreaker.java.service.UserService;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserClient userClient;
	
	@Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
	

	@Override
	public List<String> getUsers() {
		try {
	
			return userClient.getUsers();
		}
		catch (Exception e) {
			e.getMessage();
		}finally {
			io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("usercircuitbreaker");
			System.out.println("Successful call count: " + circuitBreaker.getMetrics().getNumberOfSuccessfulCalls()
					+ " | Failed call count: " + circuitBreaker.getMetrics().getNumberOfFailedCalls()
					+ " | Failure rate %:" + circuitBreaker.getMetrics().getFailureRate() 
					+ " | failure threshold size: " + circuitBreaker.getCircuitBreakerConfig().getFailureRateThreshold()
					+ " | window size: " + circuitBreaker.getCircuitBreakerConfig().getSlidingWindowSize() +
					" | State: " + circuitBreaker.getState());
		}
		return null;
	}
}
