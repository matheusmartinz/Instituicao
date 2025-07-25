package com.example.project2.study.domain.model.Biblioteca.DTO;

import com.example.project2.study.domain.model.Biblioteca.FuncionarioDTO;
import com.example.project2.study.domain.model.Biblioteca.Models.Biblioteca;
import com.example.project2.study.domain.model.Biblioteca.Models.Livro;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
public class BibliotecaDTO {
    public Long id;
    public String nome;
    public String ano;
    public EnderecoBibliotecaDTO enderecoBiblioteca;
    public List<FuncionarioDTO> funcionarios = new LinkedList<>();
    public List<LivroDTO> livros = new LinkedList<>();
    public UUID uuid;

    public BibliotecaDTO(Biblioteca biblioteca) {
        this.nome = biblioteca.getNome();
        this.ano = biblioteca.getAno();
        this.enderecoBiblioteca = new EnderecoBibliotecaDTO(biblioteca.getEnderecoBiblioteca());
        this.funcionarios = FuncionarioDTO.listOf(biblioteca.getFuncionarios());
        this.livros = LivroDTO.listOf(biblioteca.getLivros());
        this.uuid = biblioteca.getUuid();
    }
}
