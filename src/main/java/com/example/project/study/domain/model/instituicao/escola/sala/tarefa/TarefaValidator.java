package com.example.project.study.domain.model.instituicao.escola.sala.tarefa;

import com.example.project.study.exceptions.NullFieldException;
import org.springframework.stereotype.Component;

@Component
public class TarefaValidator {

    public void validateTarefas(TarefaDTO tarefaDTO) {
        if (tarefaDTO.disciplina == null) {
            throw new NullFieldException("Favor informar uma disciplina.");
        }
        if (tarefaDTO.descricao == null) {
            throw new NullFieldException("Favor informar uma descrição para a tarefa.");
        }
    }
}
