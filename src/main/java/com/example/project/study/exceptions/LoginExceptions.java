package com.example.project.study.exceptions;

import java.io.Serial;

public class LoginExceptions extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4500370249265209033L;

    public LoginExceptions(String message) {
        super(message);
    }
}
