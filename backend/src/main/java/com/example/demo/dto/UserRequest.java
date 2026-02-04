package com.example.demo.dto;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Email não pode ser nulo!")
        @Email(message = "Formato inválido de email!")
        String email,
        @NotBlank(message = "Senha não pode ser nula!")
        @Size(message = "Senha deve ter no minímo 8 caracteres", min = 8)
        String password,
        @NotBlank(message = "Nome não pode ser nulo!")
        String nome,
        @NotNull(message = "Role não pode ser nulo!")
        UserRole role
) {
    public User toEntity(){
        User usuario = new User();
        usuario.setRole(this.role);
        usuario.setEmail(this.email);
        usuario.setNome(this.nome);
        usuario.setPassword(this.password);

        return  usuario;
    }
}
