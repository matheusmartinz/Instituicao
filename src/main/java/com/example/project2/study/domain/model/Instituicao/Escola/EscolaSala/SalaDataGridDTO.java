package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.model.Instituicao.Escola.Escola;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public class SalaDataGridDTO {
    public UUID uuid;
    public String numeroSala;
    public String serieAno;
    public Integer capacidadeAlunos;
    public Integer alunos;
    public Integer professores;
    public Integer tarefas;

    public SalaDataGridDTO(Sala sala) {
        this.uuid = sala.getUuid();
        this.numeroSala = sala.getNumeroSala();
        this.serieAno = sala.getSerieAno().getValor();
        this.capacidadeAlunos = sala.getCapacidadeAlunos();
        this.alunos = ofNullable(sala.getAlunos()).orElse(List.of()).size();
        this.professores = ofNullable(sala.getProfessores()).orElse(List.of()).size();
        this.tarefas = ofNullable(sala.getTarefas()).orElse(List.of()).size();

    }
}
