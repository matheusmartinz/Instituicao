package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;

@Setter
@Getter
@Entity
public class Escola extends EntidadeIdUUID {
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_fk")
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "escola_pessoas",
            joinColumns = @JoinColumn(name = "escola_fk"),    // coluna que referencia a escola
            inverseJoinColumns = @JoinColumn(name = "pessoas_fk") // coluna que referencia a sala
    )
    private List<Pessoa> pessoas;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "escola_salas",
            joinColumns = @JoinColumn(name = "escola_fk"),    // coluna que referencia a escola
            inverseJoinColumns = @JoinColumn(name = "salas_fk") // coluna que referencia a sala
    )
    private List<Sala> salas;

    protected Escola() {
    }

    public Escola(EscolaDTO escolaDTO) {
        this.setNome(escolaDTO.nome);
        this.setSalas(Sala.listOf(escolaDTO.salas));
        this.setPessoas(Pessoa.listOfPessoa(escolaDTO.pessoas));
        this.setEndereco(new Endereco(escolaDTO.endereco));
    }

    public void addSala(Sala sala) {
        this.getSalas().add(sala);
    }


    public void initialize() {
        Hibernate.initialize(pessoas);
        Hibernate.initialize(salas);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    public void updateNome(String nome) {
        this.setNome(nome);
    }

    public void addAluno(Pessoa aluno) {
        this.getPessoas().add(aluno);
    }
}
