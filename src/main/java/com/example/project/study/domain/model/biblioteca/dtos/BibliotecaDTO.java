package com.example.project.study.domain.model.biblioteca.dtos;

import com.example.project.study.domain.model.biblioteca.FuncionarioDTO;
import com.example.project.study.domain.model.biblioteca.models.Biblioteca;
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
