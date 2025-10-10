package com.example.project.study.domain.model.instituicao.escola.sala;

import com.example.project.study.domain.model.instituicao.escola.Escola;

import java.util.UUID;

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
