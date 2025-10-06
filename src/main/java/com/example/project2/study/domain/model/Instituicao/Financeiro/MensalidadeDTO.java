package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MensalidadeDTO {
    private StatusPagamento statusPagamento;
    private PessoaDTO aluno;
    private BigDecimal valorFinal;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
}
