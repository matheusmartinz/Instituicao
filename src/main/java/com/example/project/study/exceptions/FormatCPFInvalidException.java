package com.example.project.study.exceptions;

import java.io.Serial;

public class FormatCPFInvalidException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -8471936803753837481L;

    public FormatCPFInvalidException(String message) {
        super(message);
    }
}
