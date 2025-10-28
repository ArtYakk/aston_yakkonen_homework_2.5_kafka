package com.artemyakkonen.aston_spring_boot.controller;

import com.artemyakkonen.aston_spring_boot.dto.feign.EmailRequest;
import com.artemyakkonen.aston_spring_boot.dto.feign.EmailResponse;
import com.artemyakkonen.aston_spring_boot.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody @Valid EmailRequest request) {
        EmailResponse response = emailService.sendEmail(request);
        return ResponseEntity.ok(response);
    }
}