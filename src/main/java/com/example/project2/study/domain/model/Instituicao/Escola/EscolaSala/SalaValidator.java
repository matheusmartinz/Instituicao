package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaDTO;
import org.springframework.stereotype.Component;

@Component
public class SalaValidator {
    public void validateSalaDTO(SalaDTO salaDTO) {
        if(salaDTO.numeroSala.isBlank()){
            throw new RuntimeException("Obrigatório inserir o número da sala.");
        }

        if(salaDTO.capacidadeAlunos < 0 || salaDTO.capacidadeAlunos.equals(0)) {
            throw new RuntimeException("Não há mais vagas nessa sala.");
        }

        if(salaDTO.serieAno == null){
            throw new RuntimeException("Obrigatório inserir a série da sala.");
        }
    }
}
