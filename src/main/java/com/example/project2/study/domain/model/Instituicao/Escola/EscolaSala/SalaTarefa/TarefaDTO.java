package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
public class TarefaDTO {
    public String disciplina;
    public String descricao;
    public LocalDate dataEntrega;
    public Boolean concluida;
    public UUID uuid;

    @JsonCreator
    public TarefaDTO(Tarefa tarefa) {
        this.disciplina = tarefa.getDisciplina();
        this.descricao = tarefa.getDescricao();
        this.dataEntrega = tarefa.getDataEntrega();
        this.concluida = tarefa.getConcluida();
        this.uuid = tarefa.getUuid();
    }

    public static List<TarefaDTO> listOf(List<Tarefa> tarefas) {
        return tarefas.stream().map(TarefaDTO::new).toList();
    }




}
