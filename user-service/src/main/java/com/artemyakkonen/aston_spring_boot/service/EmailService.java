package com.artemyakkonen.aston_spring_boot.service;

import com.artemyakkonen.aston_spring_boot.client.EmailServiceClient;
import com.artemyakkonen.aston_spring_boot.dto.feign.EmailRequest;
import com.artemyakkonen.aston_spring_boot.dto.feign.EmailResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailServiceClient emailServiceClient;

    @CircuitBreaker(name = "emailService", fallbackMethod = "sendEmailFallback")
    public EmailResponse sendEmail(EmailRequest request) {
        log.info("Sending email to: {}", request.getTo());
        return emailServiceClient.sendEmail(request).getBody();
    }

    private EmailResponse sendEmailFallback(EmailRequest request, Exception ex) {
        log.warn("Email service unavailable. Using fallback. Error: {}", ex.getMessage());

        return EmailResponse.builder()
                .status("QUEUED")
                .message("Email queued due to service temporary unavailability")
                .build();
    }
}