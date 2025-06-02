package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

public class UtilsFormatter {
    public static String formatCpf(String cpf) {
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9
        ));
    }

    public static String formatCep(String cep) {
        return String.format("%s-%s", cep.substring(0, 5), cep.substring(5));
    }
}
