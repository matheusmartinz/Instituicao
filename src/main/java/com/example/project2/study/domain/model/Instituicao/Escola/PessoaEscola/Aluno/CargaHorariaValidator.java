package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import org.springframework.stereotype.Component;

@Component
public class CargaHorariaValidator {
    public void validateCargaHoraria(int cargaHorario) {
        if(cargaHorario > 44) {
            throw new RuntimeException("A quantidade de disciplinas excedeu o máximo de 44 Horas");
        }
    }
}
