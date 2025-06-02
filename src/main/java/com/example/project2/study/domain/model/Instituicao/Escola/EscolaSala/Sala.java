package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.Aluno;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.Professor;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
import graphql.com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.Optional.ofNullable;

@Entity
@Getter
@Setter
public class Sala extends EntidadeIdUUID {
    @Column(unique = true)
    private String numeroSala;
    @Enumerated(EnumType.STRING)
    private SerieAno serieAno;
    private Integer capacidadeAlunos = 30;

    @OneToMany
    private List<Aluno> alunos;
    @OneToMany
    private List<Professor> professores;
    @OneToMany
    private List<Tarefa> tarefas;

    public Sala(SalaDTO salaDTO) {
        this.numeroSala = salaDTO.getNumeroSala();
        this.serieAno = salaDTO.getSerieAno();
        this.capacidadeAlunos = getCapacidadeAlunos(salaDTO, this);
        this.alunos = Aluno.listOf(salaDTO.getAlunos());
        this.professores = Professor.listOf(salaDTO.getProfessores());
        this.tarefas = Tarefa.listOf(salaDTO.getTarefas());
    }

    protected Sala() {
    }

    private static Integer getCapacidadeAlunos(SalaDTO salaDTO, Sala sala) {
        return ofNullable(salaDTO.getCapacidadeAlunos()).orElse(sala.getCapacidadeAlunos());
    }

    public static List<Sala> listOf(List<SalaDTO> salas) {
        return salas.stream().map(Sala::new).toList();
    }

    public void addAluno(Aluno aluno) {
        this.getAlunos().add(aluno);
        this.setCapacidadeAlunos(this.getCapacidadeAlunos() - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sala) {
            Sala other = (Sala) obj;
            return Objects.equal(getId(), other.getId());
        }
        return false;
    }

}
