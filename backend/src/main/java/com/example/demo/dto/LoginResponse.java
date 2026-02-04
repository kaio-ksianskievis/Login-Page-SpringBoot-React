package com.example.demo.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record LoginResponse(String token, int expire, LocalDateTime dataCreation) {
}
