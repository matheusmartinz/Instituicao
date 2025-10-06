package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor

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

    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sala> salas;


    private Escola(EscolaDTO escolaDTO) {
        this.setNome(escolaDTO.getNome());
        this.setSalas(Sala.listOf(escolaDTO.getSalas()));
        this.setPessoas(Pessoa.listOfPessoa(escolaDTO.getPessoas()));
        this.setEndereco(Endereco.of(escolaDTO.getEndereco()));
    }

    public static Escola of(EscolaDTO escolaDTO) {
        return new Escola(escolaDTO);
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
