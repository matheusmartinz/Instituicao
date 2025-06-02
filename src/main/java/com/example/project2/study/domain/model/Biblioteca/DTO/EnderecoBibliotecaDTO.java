package com.example.project2.study.domain.model.Biblioteca.DTO;


import com.example.project2.study.domain.model.Biblioteca.Enum.EstadoBiblio;
import com.example.project2.study.domain.model.Biblioteca.Models.EnderecoBiblioteca;

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
