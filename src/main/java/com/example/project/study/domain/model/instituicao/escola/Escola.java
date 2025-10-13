package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.sala.Sala;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    private List<Pessoa> pessoas = new LinkedList<>();

    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sala> salas;


    private Escola(EscolaDTO escolaDTO) {
        this.setNome(escolaDTO.getNome());
        this.setSalas(new LinkedList<>(Sala.listOf(escolaDTO.getSalas())));
        this.setPessoas( new LinkedList<>(Pessoa.listOfPessoa(escolaDTO.getPessoas())));
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

    public void updateNome(String nome) {
        this.setNome(nome);
    }

    public void addAluno(Pessoa aluno) {
        this.pessoas.add(aluno);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Escola escola = (Escola) obj;
        return Objects.equals(escola.getEndereco().getCep(), this.getEndereco().getCep())
                && Objects.equals(escola.getNome(), this.getNome())
                && Objects.equals(escola.getPessoas(), this.getPessoas())
                && Objects.equals(escola.getSalas(), this.getSalas());
    }

    public int hashCode() {
        return -1;
    }

    public boolean isEqualTo(Escola escola) {
        return this.equals( escola);
    }

}
