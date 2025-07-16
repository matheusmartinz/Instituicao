package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("Aluno")
public class Aluno extends Pessoa {
    @Column(unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SerieAno serie;
    @OneToMany
    private List<Tarefa> tarefas;
    @ElementCollection
    private List<Disciplina> disciplinas;

    public static List<Aluno> listOf(List<AlunoDTO> alunoDTOS) {
        return alunoDTOS.stream().map(Aluno::new).toList();
    }

    protected Aluno() {
    }

    public Aluno(AlunoDTO alunoDTO) {
        super(PessoaDTO.of(alunoDTO));
        this.setMatricula(alunoDTO.matricula);
        this.setSerie(SerieAno.from(alunoDTO.serieAno));
        this.setTarefas(Tarefa.listOf(alunoDTO.tarefas));
        this.setDisciplinas(alunoDTO.disciplinas);
    }

    public void initialize() {
        Hibernate.initialize(tarefas);
        Hibernate.initialize(disciplinas);
    }

    public void updateDados(AlunoDTO alunoDTO) {
        this.setNome(alunoDTO.nome);
        this.setTelefone(new PessoaTelefone(alunoDTO.telefone));
    }
}