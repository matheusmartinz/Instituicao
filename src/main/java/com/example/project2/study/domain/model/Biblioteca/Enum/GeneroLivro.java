package com.example.project2.study.domain.model.Biblioteca.Enum;

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

