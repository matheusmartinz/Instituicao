package com.example.project.study.exceptions;

import java.io.Serial;

public class ConditionFailedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8006861235505300973L;

    public ConditionFailedException(String message) {
        super(message);
    }
}
