package com.example.project.study.domain.model.carro;

import com.example.project.study.dtos.carro.CarroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TB_CARROS")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private UUID uuid;
    private String nome;
    private String ano;
    private String marca;
    private Cor cor;

    protected Carro() {}

    public Carro(CarroDTO carroDTO) {
        this.setUuid(UUID.randomUUID());
        this.setCor(Cor.fromString(carroDTO.cor));
        this.setNome(carroDTO.nome);
        this.setMarca(carroDTO.marca);
        this.setAno(carroDTO.ano);
    }
}
