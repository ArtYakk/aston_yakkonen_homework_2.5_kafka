package com.artemyakkonen.emailnotificationmicroservice.exception.userservice;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
