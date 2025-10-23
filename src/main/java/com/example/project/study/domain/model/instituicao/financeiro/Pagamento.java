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

    public Pagamento(PagamentoDTO pagamentoDTO, UUID uuidMensalidadeFK) {
        this.setStatusPagamento(pagamentoDTO.getStatusPagamento());
        this.setMensalidadeFK(uuidMensalidadeFK);
        this.setDataPagamento(pagamentoDTO.getDataPagamento());
        this.setValorPago(pagamentoDTO.getValorPago());
        this.setFormaPagamento(pagamentoDTO.getFormaPagamento());
    }

    public static Pagamento of(PagamentoDTO pagamentoDTO, UUID uuidMensalidadeFK) {
        return new Pagamento(pagamentoDTO, uuidMensalidadeFK);
    }
}
