package com.example.project2.study.domain.model.Biblioteca.Models;

import com.example.project2.study.domain.model.Biblioteca.DTO.BibliotecaDTO;
import com.example.project2.study.domain.model.Biblioteca.Funcionario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
public class


Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String nome;
    private String ano;
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoBiblioteca enderecoBiblioteca;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "funcionario_id")
    private List<Funcionario> funcionarios;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "livro_id")
    private List<Livro> livros;

    public Biblioteca(BibliotecaDTO bibliotecaDTO) {
        this.setNome(bibliotecaDTO.nome);
        this.setAno(bibliotecaDTO.ano);
        this.setEnderecoBiblioteca(new EnderecoBiblioteca(bibliotecaDTO.enderecoBiblioteca));
        this.setFuncionarios(Funcionario.listOf(bibliotecaDTO.funcionarios));
        this.setLivros(Livro.listOf(bibliotecaDTO.livros));
        this.setUuid(UUID.randomUUID());
    }

    protected Biblioteca() {}
}
