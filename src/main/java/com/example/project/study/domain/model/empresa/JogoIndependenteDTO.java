package com.example.project.study.domain.model.empresa;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class JogoIndependenteDTO {
    public UUID uuid;

    @NotBlank
    public String titulo;
    @NotEmpty
    public List<Genero> generos = new LinkedList<>();
    @NotNull
    public Plataforma plataforma;
    @NotNull
    public LocalDate dataLancamento;
    @NotNull
    @DecimalMin("0.0")
    public BigDecimal preco;
    public UUID uuidEmpresaDesenvolvedora;
    @NotBlank
    public String nomeEquipe;
    @NotNull
    public Boolean isFinanciado;

    public JogoIndependenteDTO(JogoIndependente jogoIndependente) {
        this.titulo = jogoIndependente.getTitulo();
        this.generos = jogoIndependente.getGeneros();
        this.plataforma = jogoIndependente.getPlataforma();
        this.dataLancamento = jogoIndependente.getDataLancamento();
        this.preco = jogoIndependente.getPreco();
        this.uuid = jogoIndependente.getUuid();
        this.nomeEquipe = jogoIndependente.getNomeEquipe();
        this.isFinanciado = jogoIndependente.getIsFinanciado();
        this.uuidEmpresaDesenvolvedora = jogoIndependente.getEmpresaDesenvolvedora().getUuid();
    }
}
