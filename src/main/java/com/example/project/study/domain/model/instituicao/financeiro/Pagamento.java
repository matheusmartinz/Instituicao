package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Pagamento extends EntidadeIdUUID {
    private StatusPagamento statusPagamento;
    private UUID mensalidadeFK;
    private LocalDate dataPagamento;
    private BigDecimal valorPago;
    private FormaPagamento formaPagamento;

    public Pagamento(PagamentoDTO pagamentoDTO) {
        this.setStatusPagamento(pagamentoDTO.getStatusPagamento());
        this.setMensalidadeFK(pagamentoDTO.getMensalidadeFK());
        this.setDataPagamento(pagamentoDTO.getDataPagamento());
        this.setValorPago(pagamentoDTO.getValorPago());
        this.setFormaPagamento(pagamentoDTO.getFormaPagamento());
    }

    private Pagamento(FinanceiroDTO financeiroDTO) {
        this.setStatusPagamento(financeiroDTO.getPagamentoDTO().getStatusPagamento());
        this.setMensalidadeFK(financeiroDTO.getPagamentoDTO().getMensalidadeFK());
        this.setDataPagamento(financeiroDTO.getPagamentoDTO().getDataPagamento());
        this.setValorPago(financeiroDTO.getPagamentoDTO().getValorPago());
        this.setFormaPagamento(financeiroDTO.getPagamentoDTO().getFormaPagamento());
    }

    public static Pagamento of(PagamentoDTO pagamentoDTO) {
        return new Pagamento(pagamentoDTO);
    }

    public static Pagamento of(FinanceiroDTO financeiroDTO) {
        return new Pagamento(financeiroDTO);
    }
}
