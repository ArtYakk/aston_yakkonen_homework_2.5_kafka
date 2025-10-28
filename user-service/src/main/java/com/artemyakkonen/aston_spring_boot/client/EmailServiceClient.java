package com.artemyakkonen.aston_spring_boot.client;

import com.artemyakkonen.aston_spring_boot.dto.feign.EmailRequest;
import com.artemyakkonen.aston_spring_boot.dto.feign.EmailResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("email-notification-service")
public interface EmailServiceClient {
    @PostMapping("/api/email")
    ResponseEntity<EmailResponse> sendEmail(@RequestBody @Valid EmailRequest request);
}
