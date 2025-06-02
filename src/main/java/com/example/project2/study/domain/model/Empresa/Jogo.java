package com.example.project2.study.domain.model.Empresa;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import graphql.com.google.common.base.Objects;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_JOGO")
public class Jogo extends EntidadeIdUUID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private UUID uuid;
    @NotNull
    private String titulo;
    @NotNull
    private List<Genero> generos;
    @NotNull
    private Plataforma plataforma;
    private LocalDate dataLancamento;
    @NotNull
    private BigDecimal preco;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaDesenvolvedora empresaDesenvolvedora;

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
