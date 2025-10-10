package com.example.project.study.domain.model.carro;

public enum Cor {
    AZUL,
    VERMELHO,
    VERDE,
    PRETO;

    public static Cor fromString(String valor) {
        try {
            if (valor == null) return null;
            return Cor.valueOf(valor.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Cor inválida: " + valor + " ,você deve escolher apenas entre: Azul, Vermelho, Verde ou Preto ");
        }
    }


}


