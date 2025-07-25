package com.example.project2.study.domain.model.Biblioteca.DTO;

import com.example.project2.study.domain.model.Biblioteca.Enum.GeneroLivro;
import com.example.project2.study.domain.model.Biblioteca.Models.Livro;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class LivroDTO {
    public UUID uuid = UUID.randomUUID();
    public String titulo;
    public String autor;
    public String ano;
    public GeneroLivro genero;

    public LivroDTO(Livro saveLivro) {
        this.titulo = saveLivro.getTitulo();
        this.autor = saveLivro.getAutor();
        this.ano = saveLivro.getAno();
        this.genero = saveLivro.getGenero();
    }

    public static List<LivroDTO> listOf(List<Livro> livros) {
        return livros.stream().map(LivroDTO::new).toList();
    }
}
