package com.example.project.study.domain.model.biblioteca.dtos;


import com.example.project.study.domain.model.biblioteca.enums.EstadoBiblio;
import com.example.project.study.domain.model.biblioteca.models.EnderecoBiblioteca;

import java.util.UUID;

public class EnderecoBibliotecaDTO {
    public String nome;
    public String cep;
    public String numero;
    public EstadoBiblio estadoBiblio;
    public UUID uuid;

    public EnderecoBibliotecaDTO(EnderecoBiblioteca enderecoBiblioteca) {
        this.nome = enderecoBiblioteca.getNome();
        this.cep = enderecoBiblioteca.getCep();
        this.estadoBiblio = enderecoBiblioteca.getEstadoBiblio();
        this.numero = enderecoBiblioteca.getNumero();
        this.uuid = enderecoBiblioteca.getUuid();
    }
}
