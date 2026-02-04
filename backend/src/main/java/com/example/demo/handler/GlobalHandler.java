package com.example.demo.handler;

import com.example.demo.dto.FieldResponse;
import com.example.demo.dto.MessageErrorResponse;
import com.example.demo.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<MessageErrorResponse<String>> regraDeNegocio(RegraDeNegocioException e){
         MessageErrorResponse<String> response = new MessageErrorResponse<>(e.getMessage(), LocalDateTime.now());

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<MessageErrorResponse<List<FieldResponse>>> methorInvalid(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getFieldErrors();

        List<FieldResponse> new_error = errors.stream()
                .map(error -> new FieldResponse(error.getField(),error.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorResponse<>(new_error,LocalDateTime.now()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public  ResponseEntity<MessageErrorResponse<String>>  usernameNotFound(UsernameNotFoundException e){
        MessageErrorResponse<String> response = new MessageErrorResponse<>(e.getMessage(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<MessageErrorResponse<String>> badcredenciais(BadCredentialsException e){
        MessageErrorResponse<String> response = new MessageErrorResponse<>("Usuário eu senha inválidos",LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
