package com.artemyakkonen.emailnotificationmicroservice.service;

import com.artemyakkonen.emailnotificationmicroservice.client.UserClient;

import com.artemyakkonen.emailnotificationmicroservice.exception.userservice.UserNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserClient userClient;

    @CircuitBreaker(name = "userServiceCB", fallbackMethod = "fallBackCheckEmail")
    public void checkEmail(String email){
         userClient.checkEmail(email);
    }

    public void fallBackCheckEmail(String email, Throwable t) {
        if(t instanceof UserNotFoundException) {
            throw new UserNotFoundException("User not found");
        }else {
           throw new RuntimeException("From fallback: Not handled exception");
        }

    }
}
