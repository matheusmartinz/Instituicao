package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
public class Mensalidade extends EntidadeIdUUID {
    @Enumerated(EnumType.STRING)
    @Setter
    private StatusPagamento statusPagamento;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pessoa_fk")
    private Pessoa aluno;
    @Setter
    private BigDecimal valorFinal;
    @Setter
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;


    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
