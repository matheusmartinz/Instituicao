package com.example.project.study.domain.model.instituicao.escola;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum SerieAno {
    PRIMEIRO_ANO("1°"),
    SEGUNDO_ANO("2°"),
    TERCEIRO_ANO("3°"),
    QUARTO_ANO("4°"),
    QUINTO_ANO("5°"),
    SEXTO_ANO("6°"),
    SETIMO_ANO("7°"),
    OITAVO_ANO("8°"),
    NONO_ANO("9°");

    private final String valor;

    SerieAno(String valor) {
        this.valor = valor;
    }

    @JsonCreator
    public static SerieAno from(String valor) {
        if (valor == null)
            return SerieAno.PRIMEIRO_ANO;
        for (SerieAno ano : SerieAno.values()) {
            if (ano.valor.equalsIgnoreCase(valor)) {
                return ano;
            }
        }
        throw new IllegalArgumentException("Ano não cadastrado: " + valor);
    }

    public String getValor() {
        return valor;
    }

    public String getDescritivo() {
        return Arrays.stream(this.name().split("_"))
                .map(String::toLowerCase)
                .reduce((a, b) -> a + " " + b)
                .orElse(this.name());
    }

    public static List<String> getTodosValores() {
        return Arrays.stream(values()).map(SerieAno::getValor).toList();
    }

    public boolean isAno(SerieAno serieAno) {
        return this == serieAno;
    }
}
