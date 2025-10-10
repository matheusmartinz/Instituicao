package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private LocalDate dataPagamento;
    private BigDecimal valorPago;

    private Mensalidade(MensalidadeDTO mensalidadeDTO, UUID alunoUUID) {
        this.setDataEmissao(mensalidadeDTO.getDataEmissao());

        this.setStatusPagamento(mensalidadeDTO.getStatusPagamento());
        this.setValorPago(mensalidadeDTO.getValorPago());
        this.setAlunoFK(alunoUUID);
        this.setValorPagamento(mensalidadeDTO.getValorPagamento());
        this.setJuros(mensalidadeDTO.getJuros());
        this.setMulta(mensalidadeDTO.getMulta());

        this.setDataVencimento(this.getDataEmissao().plusMonths(1L));
        this.setDataPagamento(mensalidadeDTO.getDataPagamento());
    }

    public static Mensalidade of(MensalidadeDTO mensalidadeDTO, UUID alunoUUID) {
        return new Mensalidade(mensalidadeDTO, alunoUUID);
    }

    public boolean isPago() {
        return statusPagamento == StatusPagamento.PAGO;
    }

    public BigDecimal updateValorPagamento(MensalidadeDTO mensalidadeDTO) {
        if (isVencido()) {
            BigDecimal novaMulta = getMulta().multiply(getValorPagamento()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);
            BigDecimal novoJuros = getJuros().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(getDataVencimento(), LocalDate.now())));

            return getValorPagamento().add(novaMulta).add(novoJuros);
        }
        return mensalidadeDTO.getValorPagamento();
    }

    public boolean isVencido() {
        return LocalDate.now().isAfter(getDataVencimento());
    }
}
