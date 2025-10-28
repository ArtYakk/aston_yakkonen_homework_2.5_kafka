package com.artemyakkonen.aston_spring_boot.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    private String id;
    private String status;
    private String message;
    private LocalDateTime timestamp;
}