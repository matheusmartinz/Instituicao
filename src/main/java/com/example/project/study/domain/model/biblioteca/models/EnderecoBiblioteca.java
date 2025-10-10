package com.example.project.study.domain.model.biblioteca.models;

import com.example.project.study.domain.model.biblioteca.dtos.EnderecoBibliotecaDTO;
import com.example.project.study.domain.model.biblioteca.enums.EstadoBiblio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
public class EnderecoBiblioteca {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private EstadoBiblio estadoBiblio;
    private String cep;
    private String numero;
    private UUID uuid;

    protected EnderecoBiblioteca() {}

    public  EnderecoBiblioteca (EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
        this.setNome(enderecoBibliotecaDTO.nome);
        this.setEstadoBiblio(enderecoBibliotecaDTO.estadoBiblio);
        this.setCep(enderecoBibliotecaDTO.cep);
        this.setNumero(enderecoBibliotecaDTO.numero);
        this.setUuid(UUID.randomUUID());
    }
}
