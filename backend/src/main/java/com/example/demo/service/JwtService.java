package com.example.demo.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String key;

    private Instant tempo = Instant.now().plusSeconds(7200);

    public String generateToken(User user)  {
        try{
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT
                    .create()
                    .withIssuer("teste")
                    .withExpiresAt(tempo)
                    .withSubject(user.getEmail())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao criar token",e);
        }
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return  JWT
                    .require(algorithm)
                    .withIssuer("teste")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch(JWTVerificationException e){
            return "";
        }
    }
}
