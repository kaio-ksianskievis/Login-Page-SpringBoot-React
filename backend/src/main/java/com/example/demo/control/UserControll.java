package com.example.demo.control;


import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserControll {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/home")
    public ResponseEntity<UserResponse> getHome(@AuthenticationPrincipal User usuario){
        UserResponse response = new UserResponse(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserResponse>> showAllUser(){
        return  ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public ResponseEntity<String> message(){
        return  ResponseEntity.status(HttpStatus.OK).body("Usuário logado!");
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserResponse> showUserById(@PathVariable Integer id){
        return  ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String>  deleteUserByEmail(@PathVariable Integer id){
        return  ResponseEntity.status(HttpStatus.OK).body(service.deleteUsuarios(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUsers(@RequestBody @Valid UserRequest body){
        User usuario = body.toEntity();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(usuario));
    }

    @PostMapping("/verify")
    public  ResponseEntity<CodeResponse> verificarCodigo(@RequestBody @Valid CodeRequest body){
        service.verificaUsuario(body.code());

        return  ResponseEntity.status(HttpStatus.OK).body(new CodeResponse("Usuário verificado!"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest body){
        var authUser = new UsernamePasswordAuthenticationToken(body.email(),body.password());
        var auth = authenticationManager.authenticate(authUser);
        String token = jwtService.generateToken((User) auth.getPrincipal());

        LoginResponse response = new LoginResponse(token,7200, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
