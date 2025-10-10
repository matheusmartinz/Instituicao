package com.example.project.study.domain.model.instituicao.escola.pessoa.aluno;

import com.example.project.study.exceptions.ConditionFailedException;
import org.springframework.stereotype.Component;

@Component
public class CargaHorariaValidator {
    public void validateCargaHoraria(int cargaHorario) {
        if (cargaHorario > 44) {
            throw new ConditionFailedException("A quantidade de disciplinas excedeu o máximo de 44 Horas");
        }
    }
}
