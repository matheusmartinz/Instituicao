package com.example.project.study.config;

import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handleSemTokenException(
            EntidadeNaoEncontradaException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", Instant.now().toString());
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "bad_request");
        errors.put("message", ex.getMessage());
        errors.put("path", request.getRequestURI());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}





