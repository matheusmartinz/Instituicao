package com.example.project.study.exceptions;

import java.io.Serial;

public class StudyExceptions extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6095465544337078525L;

    public StudyExceptions(String message) {
        super(message);
    }
}
