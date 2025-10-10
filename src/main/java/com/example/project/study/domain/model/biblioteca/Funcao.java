package com.example.project.study.domain.model.biblioteca;

import com.example.project.study.exceptions.NullFieldException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum Funcao {
    COORDENADOR,
    FAXINEIRO,
    RESTAURADOR,
    ORGANIZADOR,
    ATENDIMENTO;

    @JsonCreator
    public static Funcao escrita(String value) {
        if (value == null) {
            throw new NullFieldException("Num pode");
        }

        try {
            return Funcao.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Função inválida: " + value);
        }
    }
}
