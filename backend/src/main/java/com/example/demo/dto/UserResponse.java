package com.example.demo.dto;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;

public record UserResponse(Integer id, String email, String nome, UserRole role) {

    public UserResponse(User user) {
        this(user.getId(), user.getEmail(), user.getNome(), user.getRole());
    }

}
