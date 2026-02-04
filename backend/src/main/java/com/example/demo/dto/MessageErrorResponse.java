package com.example.demo.dto;

import java.time.LocalDateTime;

public record MessageErrorResponse<T>( T error, LocalDateTime dataHora) {
}
