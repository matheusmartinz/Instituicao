package com.example.project2.study.domain.model.Biblioteca.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.text.Normalizer;
import java.util.Map;

public enum EstadoBiblio {
    AC, AL, AP,
    AM, BA, CE,
    DF, ES, GO,
    MA, MT, MS,
    MG, PA, PB,
    PR, PE, PI,
    RJ, RN, RS,
    RO, RR, SC,
    SP, SE, TO;

    @JsonCreator
    public static EstadoBiblio from(String value) {
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Estado não cadastrado, entrar em contato com a Administração");
        }

        String nomeNormalizado = Normalizer.normalize(value.trim().toUpperCase(), Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Mapeamento manual dos nomes normalizados para siglas
        Map<String, String> nomeParaSigla = Map.ofEntries(
                Map.entry("ACRE", "AC"),
                Map.entry("ALAGOAS", "AL"),
                Map.entry("AMAPA", "AP"),
                Map.entry("AMAZONAS", "AM"),
                Map.entry("BAHIA", "BA"),
                Map.entry("CEARA", "CE"),
                Map.entry("DISTRITO FEDERAL", "DF"),
                Map.entry("ESPIRITO SANTO", "ES"),
                Map.entry("GOIAS", "GO"),
                Map.entry("MARANHAO", "MA"),
                Map.entry("MATO GROSSO", "MT"),
                Map.entry("MATO GROSSO DO SUL", "MS"),
                Map.entry("MINAS GERAIS", "MG"),
                Map.entry("PARA", "PA"),
                Map.entry("PARAIBA", "PB"),
                Map.entry("PARANÁ", "PR"),
                Map.entry("PERNAMBUCO", "PE"),
                Map.entry("PIAUI", "PI"),
                Map.entry("RIO DE JANEIRO", "RJ"),
                Map.entry("RIO GRANDE DO NORTE", "RN"),
                Map.entry("RIO GRANDE DO SUL", "RS"),
                Map.entry("RONDONIA", "RO"),
                Map.entry("RORAIMA", "RR"),
                Map.entry("SANTA CATARINA", "SC"),
                Map.entry("SAO PAULO", "SP"),
                Map.entry("SERGIPE", "SE"),
                Map.entry("TOCANTINS", "TO")
                // Adicione outros estados conforme necessário
        );

        String sigla = nomeParaSigla.get(nomeNormalizado);

        if (sigla == null) {
            throw new RuntimeException("Estado inválido: " + value);
        }

        return EstadoBiblio.valueOf(sigla);
    }
}
