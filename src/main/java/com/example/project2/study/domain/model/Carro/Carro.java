package com.example.project2.study.domain.model.Carro;

import com.example.project2.study.dtos.Carro.CarroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.util.annotation.Nullable;

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
