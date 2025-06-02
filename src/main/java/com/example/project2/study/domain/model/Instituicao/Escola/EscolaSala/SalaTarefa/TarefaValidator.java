package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa;

import org.springframework.stereotype.Component;

@Component
public class TarefaValidator {

    public void validateTarefas(TarefaDTO tarefaDTO) {
        if (tarefaDTO.disciplina == null) {
            throw new RuntimeException("Favor informar uma disciplina.");
        }
        if (tarefaDTO.descricao == null) {
            throw new RuntimeException("Favor informar uma descrição para a tarefa.");
        }
    }
}
