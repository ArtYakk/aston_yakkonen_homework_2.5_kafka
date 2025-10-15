package com.artemyakkonen.aston_spring_boot.service;

public interface EmailService {
    void send(String email, String message);
}
