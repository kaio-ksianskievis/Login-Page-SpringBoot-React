package com.example.demo.dto;

import com.example.demo.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "Email não pode ser nulo!")
        String email,
        @NotBlank(message = "Senha não pode ser nula!")
        String password
) {
    public User toEntity(){
        User usuario = new User();
        usuario.setEmail(this.email);
        usuario.setPassword(this.password);

        return  usuario;
    }
}
