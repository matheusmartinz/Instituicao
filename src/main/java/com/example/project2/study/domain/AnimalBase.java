package com.example.project2.study.domain;

import com.example.project2.study.domain.model.Carro.Cor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AnimalBase {
    public String name;
    public Cor cor;
    public Porte porte;
}
