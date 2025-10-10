package com.example.project.study.domain.model.biblioteca.enums;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum GeneroLivro {
    ROMANCE,
    SUSPENSE,
    TERROR,
    AVENTURA;

    @JsonCreator
    public static GeneroLivro from(String value) {
        if (value == null) {
            return null;
        }
        return GeneroLivro.valueOf(value.toUpperCase());
    }
}

