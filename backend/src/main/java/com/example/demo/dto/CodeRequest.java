package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CodeRequest(
        @NotNull(message = "código não pode ser nulo")
        @Size(min = 6, message = "código deve ter pelo menos 6 caracteres")
        String code) {
}
