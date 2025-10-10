package com.example.project.study.domain.model.instituicao.escola.sala;

import com.example.project.study.exceptions.ConditionFailedException;
import com.example.project.study.exceptions.NullFieldException;
import org.springframework.stereotype.Component;

@Component
public class SalaValidator {

    public void validateSalaDTO(SalaDTO salaDTO) {
        if (salaDTO.numeroSala.isBlank()) {
            throw new NullFieldException("Obrigatório inserir o número da sala.");
        }

        if (salaDTO.capacidadeAlunos < 0 || salaDTO.capacidadeAlunos.equals(0)) {
            throw new ConditionFailedException("Não há mais vagas nessa sala.");
        }

        if (salaDTO.serieAno == null) {
            throw new NullFieldException("Obrigatório inserir a série da sala.");
        }
    }
}
