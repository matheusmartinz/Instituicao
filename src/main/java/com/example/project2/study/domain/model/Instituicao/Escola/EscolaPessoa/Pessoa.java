package com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.CargaHorariaPorDisciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Entity
@Getter
@Setter
public class Pessoa extends EntidadeIdUUID {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_telefone")
    private PessoaTelefone telefone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco", nullable = false)
    private Endereco endereco;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escola_fk")
    private Escola escola;
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    @ElementCollection
    private List<Disciplina> disciplinas = new LinkedList<>();


    // Dados aluno
    @Column(unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SerieAno serie;
    @OneToMany
    private List<Tarefa> tarefas = new LinkedList<>();
    private Integer cargaHoraria;


    //Dados Professor
    private String quantidadeAulas;

    protected Pessoa() {
    }

    public static List<Pessoa> listOfPessoa(List<PessoaDTO> pessoas) {
        return pessoas.stream().map(Pessoa::new).toList();
    }

    public List<Disciplina> getDisciplinas() {
        return List.copyOf(disciplinas);
    }

    protected Pessoa(PessoaDTO pessoaDTO) {
        this.setNome(pessoaDTO.getNome());
        this.setCpf(pessoaDTO.getCpf());
        this.setEmail(pessoaDTO.getEmail());
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
    }

    public Pessoa(AlunoDTO pessoaDTO) {
        this.setNome(pessoaDTO.getNome());
        this.setCpf(pessoaDTO.getCpf());
        this.setEmail(pessoaDTO.getEmail());
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
        this.setMatricula(pessoaDTO.getMatricula());
        this.setTipoPessoa(TipoPessoa.ALUNO);
        this.setSerie(SerieAno.from(pessoaDTO.getSerieAno()));
        this.setDisciplinas(pessoaDTO.getDisciplinas());
        this.setCargaHoraria(pessoaDTO.getCargaHoraria());
    }



    private static Endereco getEndereco(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.getEndereco())
                .map(e -> Endereco.of(pessoaDTO.getEndereco()))
                .orElse(null);
    }

    private static PessoaTelefone getTelefone(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.getTelefone())
                .map(e -> new PessoaTelefone(pessoaDTO.getTelefone()))
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    public void updateDados(AlunoDTO alunoDTO) {
        this.setNome(alunoDTO.getNome());
        this.setTelefone(new PessoaTelefone(alunoDTO.getTelefone()));
        this.setSerie(SerieAno.from(alunoDTO.getSerieAno()));
        this.setDisciplinas(alunoDTO.getDisciplinas());
    }

    public void removeDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
        updateCargaHoraria();
    }

    public void updateDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas.clear();
        this.disciplinas.addAll(disciplinas);
        updateCargaHoraria();
    }

    public void updateDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
        updateCargaHoraria();
    }

    private void updateCargaHoraria() {
        this.setCargaHoraria(CargaHorariaPorDisciplina.getCargaHoraria(this.disciplinas, this.serie.getValor()));
    }
}
