package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import graphql.com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Mensalidade extends EntidadeIdUUID {
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private UUID alunoFK;
    private BigDecimal valorPagamento;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private BigDecimal juros = BigDecimal.ZERO;
    private BigDecimal multa = BigDecimal.ZERO;
    private LocalDateTime dataPagamento;
    private BigDecimal valorPago;

    private Mensalidade(MensalidadeDTO mensalidadeDTO, UUID alunoUUID) {
        this.setDataEmissao(mensalidadeDTO.getDataEmissao());

        this.setStatusPagamento(mensalidadeDTO.getStatusPagamento());
        this.setValorPago(mensalidadeDTO.getValorPago());
        this.setAlunoFK(alunoUUID);

        this.setDataVencimento(this.getDataEmissao().plusMonths(1L));
    }

    public static Mensalidade of(MensalidadeDTO mensalidadeDTO, UUID alunoUUID) {
        return new Mensalidade(mensalidadeDTO, alunoUUID);
    }


    @Override
    public boolean equals(Object obj) {
//        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mensalidade mensalidade = (Mensalidade) obj;
        return Objects.equal(mensalidade.getUuid(), this.getUuid());
    }

    public boolean isPago() {
        return statusPagamento == StatusPagamento.PAGO;
    }
}
