package com.example.project2.study.dtos.Carro;

import com.example.project2.study.domain.model.Carro.Carro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CarroDTO {
    public String nome;
    public String marca;
    public String ano;
    public String cor;
    public String uuid;

    public CarroDTO(Carro carro) {
        this.nome = carro.getNome();
        this.ano = carro.getAno();
        this.cor = carro.getCor().toString();
        this.marca = carro.getMarca();
        this.uuid = carro.getUuid().toString();
    }

    public String getDescritivo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (nome != null && !nome.isBlank()) {
            stringBuilder.append(String.format("%s", nome));
        }
        if (cor != null && !cor.isBlank()) {
            stringBuilder.append(String.format(" - %s", cor));
        }

        return stringBuilder.toString();
    }
}
