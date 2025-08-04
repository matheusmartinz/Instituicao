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
    public String escolaUUID;
    public String escolaDescricao;

    public SalaDataGridDTO(Sala sala) {
        this.uuid = sala.getUuid();
        this.numeroSala = sala.getNumeroSala();
        this.serieAno = sala.getSerieAno().getValor();
        this.capacidadeAlunos = sala.getCapacidadeAlunos();
        this.alunos =  sala.getAlunos().size();
        this.professores = sala.getProfessores().size();
        this.tarefas = sala.getTarefas().size();

        Escola escola = sala.getEscola();
        if (escola != null) {
            this.escolaUUID = escola.getUuid().toString();
            this.escolaDescricao = escola.getNome();
        } else {
            this.escolaUUID = null;
            this.escolaDescricao = "Escola não associada";
        }
    }
}
