package com.example.project2.study.domain.model.Biblioteca.Models;

import com.example.project2.study.domain.model.Biblioteca.Enum.GeneroLivro;
import com.example.project2.study.domain.model.Biblioteca.DTO.LivroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String titulo;
    private String autor;
    private String ano;
    private GeneroLivro genero;
    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    public Livro(LivroDTO livroDTO) {
        this.setTitulo(livroDTO.titulo);
        this.setAutor(livroDTO.autor);
        this.setAno(livroDTO.ano);
        this.setGenero(livroDTO.genero);
        this.setUuid(livroDTO.uuid);
    }

    protected Livro() {}

    public static List<Livro> listOf(List<LivroDTO> livrosDTO) {
        return livrosDTO.stream().map(Livro::new).toList();
    }
}
