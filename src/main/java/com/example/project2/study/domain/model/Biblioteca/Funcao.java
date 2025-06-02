package com.example.project2.study.domain.model.Biblioteca;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.text.Normalizer;

public enum Funcao {
    COORDENADOR,
    FAXINEIRO,
    RESTAURADOR,
    ORGANIZADOR,
    ATENDIMENTO;

    @JsonCreator
    public static Funcao escrita(String value) {
        if(value == null) {
            throw new RuntimeException("Num pode");
        }

        String normalizado = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // remove acentos
                .replaceAll("[^\\p{IsAlphabetic}]", "") // remove caracteres não alfabéticos
                .trim()
                .toUpperCase();

        try {
            return Funcao.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Função inválida: " + value);
        }
    }
}
