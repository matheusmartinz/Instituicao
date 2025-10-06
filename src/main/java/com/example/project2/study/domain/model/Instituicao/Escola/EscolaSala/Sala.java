package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
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
    @Column(unique = true, nullable = false)
    private String numeroSala;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SerieAno serieAno;
    @Column(nullable = false)
    private Integer capacidadeAlunos = 30;

    @OneToMany
    private List<Pessoa> alunos;
    @OneToMany
    private List<Pessoa> professores;
    @OneToMany
    private List<Tarefa> tarefas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "escola_fk")
    private Escola escola;

    public Sala(SalaDTO salaDTO) {
        this.numeroSala = salaDTO.getNumeroSala();
        this.serieAno = salaDTO.getSerieAno();
        this.capacidadeAlunos = getCapacidadeAlunos(salaDTO, this);
        this.alunos = Pessoa.listOfPessoa(salaDTO.getAlunos());
        this.professores = Pessoa.listOfPessoa(salaDTO.getProfessores());
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

    public void addAluno(Pessoa aluno) {
        this.getAlunos().add(aluno);
        this.setCapacidadeAlunos(this.getCapacidadeAlunos() - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sala sala = (Sala) obj;
        return Objects.equal(sala.getUuid(), this.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getUuid());
    }

    public void updateSala(SalaDTO salaDTO) {
        this.numeroSala = salaDTO.getNumeroSala();
        this.serieAno = salaDTO.getSerieAno();
    }
}
