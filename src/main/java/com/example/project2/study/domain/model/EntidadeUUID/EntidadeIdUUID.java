package com.example.project2.study.domain.model.EntidadeUUID;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadeIdUUID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    public abstract boolean equals(Object obj);
}
