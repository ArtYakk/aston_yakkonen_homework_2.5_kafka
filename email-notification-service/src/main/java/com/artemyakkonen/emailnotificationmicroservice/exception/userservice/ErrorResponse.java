package com.artemyakkonen.emailnotificationmicroservice.exception.userservice;

public record ErrorResponse(int status, String code, String message) {}