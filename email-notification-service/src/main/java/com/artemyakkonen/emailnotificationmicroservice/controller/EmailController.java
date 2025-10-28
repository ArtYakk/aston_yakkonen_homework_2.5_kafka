package com.artemyakkonen.emailnotificationmicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {

    }
}
