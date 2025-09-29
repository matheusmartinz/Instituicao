package com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    //Dados Professor
    private String quantidadeAulas;

    protected Pessoa() {
    }

    public static List<Pessoa> listOfPessoa(List<PessoaDTO> pessoas) {
        return pessoas.stream().map(Pessoa::new).toList();
    }

    protected Pessoa(PessoaDTO pessoaDTO) {
        this.setNome(pessoaDTO.nome);
        this.setCpf(pessoaDTO.cpf);
        this.setEmail(pessoaDTO.email);
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
    }

    public Pessoa(AlunoDTO pessoaDTO) {
        this.setNome(pessoaDTO.nome);
        this.setCpf(pessoaDTO.cpf);
        this.setEmail(pessoaDTO.email);
        this.setTelefone(getTelefone(pessoaDTO));
        this.setEndereco(getEndereco(pessoaDTO));
        this.setMatricula(pessoaDTO.matricula);
        this.setTipoPessoa(TipoPessoa.ALUNO);
        this.setSerie(SerieAno.from(pessoaDTO.serieAno));
    }

    private static Endereco getEndereco(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.endereco)
                .map(e -> new Endereco(pessoaDTO.endereco))
                .orElse(null);
    }

    private static PessoaTelefone getTelefone(PessoaDTO pessoaDTO) {
        return ofNullable(pessoaDTO.telefone)
                .map(e -> new PessoaTelefone(pessoaDTO.telefone))
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    public void updateDados(AlunoDTO alunoDTO) {
        this.setNome(alunoDTO.nome);
        this.setTelefone(new PessoaTelefone(alunoDTO.telefone));
        this.setSerie(SerieAno.from(alunoDTO.serieAno));
    }
}
