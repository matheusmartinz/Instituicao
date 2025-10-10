package com.example.project.study.domain;

import com.example.project.study.domain.model.carro.Cor;
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
