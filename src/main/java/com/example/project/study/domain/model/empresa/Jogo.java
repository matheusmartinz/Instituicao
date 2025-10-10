package com.example.project.study.domain.model.empresa;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
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

}
