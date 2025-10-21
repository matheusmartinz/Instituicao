package com.example.project.study.domain.model.instituicao.financeiro;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class PagamentoDTO extends EntidadeIdUUIDDTO{
    private StatusPagamento statusPagamento;
    private UUID mensalidadeFK;
    private LocalDate dataPagamento;
    private BigDecimal valorPago;
    private FormaPagamento formaPagamento;

    public PagamentoDTO(Pagamento pagamento) {
        this.setStatusPagamento(pagamento.getStatusPagamento());
        this.setMensalidadeFK(pagamento.getMensalidadeFK());
        this.setDataPagamento(pagamento.getDataPagamento());
        this.setValorPago(pagamento.getValorPago());
        this.setFormaPagamento(pagamento.getFormaPagamento());
        this.setUuid(pagamento.getUuid());
        this.setId(pagamento.getId());
    }

    public PagamentoDTO(FinanceiroDTO financeiroDTO) {
        this.setStatusPagamento(financeiroDTO.getPagamentoDTO().getStatusPagamento());
        this.setMensalidadeFK(financeiroDTO.getPagamentoDTO().getMensalidadeFK());
        this.setDataPagamento(financeiroDTO.getPagamentoDTO().getDataPagamento());
        this.setValorPago(financeiroDTO.getPagamentoDTO().getValorPago());
        this.setFormaPagamento(financeiroDTO.getPagamentoDTO().getFormaPagamento());
    }

    public static PagamentoDTO of(Pagamento pagamento) {
        return new PagamentoDTO(pagamento);
    }

    public PagamentoDTO(){}

    public static PagamentoDTO of(FinanceiroDTO financeiroDTO) {
        return new PagamentoDTO(financeiroDTO);
    }
}
