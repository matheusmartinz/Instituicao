package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.exceptions.ConditionFailedException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MensalidadeDTO extends EntidadeIdUUIDDTO {
    private StatusPagamento statusPagamento = StatusPagamento.EM_ABERTO;
    private UUID alunoFK;
    private BigDecimal valorPagamento = BigDecimal.ZERO;
    private LocalDate dataVencimento;
    private LocalDate dataEmissao = LocalDate.now();
    private BigDecimal juros = BigDecimal.ZERO;
    private BigDecimal multa = BigDecimal.ZERO;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private LocalDate dataPagamento;

    private MensalidadeDTO(Mensalidade mensalidade) {
        super.setId(mensalidade.getId());
        super.setUuid(mensalidade.getUuid());

        this.setStatusPagamento(mensalidade.getStatusPagamento());
        this.setValorPago(mensalidade.getValorPago());
        this.setDataEmissao(mensalidade.getDataEmissao());
        this.setDataVencimento(mensalidade.getDataVencimento());
        this.setAlunoFK(mensalidade.getAlunoFK());
        this.setValorPagamento(mensalidade.getValorPagamento());
        this.setJuros(mensalidade.getJuros());
        this.setMulta(mensalidade.getMulta());
        this.setDataPagamento(mensalidade.getDataPagamento());
    }

    public MensalidadeDTO(FinanceiroDTO financeiroDTO) {
        this.setValorPagamento(financeiroDTO.getValorPago());
    }

    public static MensalidadeDTO of(Mensalidade mensalidade) {
        return new MensalidadeDTO(mensalidade);
    }

    public static MensalidadeDTO of(FinanceiroDTO financeiroDTO) {
        return new MensalidadeDTO(financeiroDTO);
    }

    public void setJuros(BigDecimal juros) {
        if (juros.compareTo(BigDecimal.ZERO) > 0 && juros.compareTo(BigDecimal.valueOf(100)) <= 0) {
            this.juros = juros;
        } else {
            throw new ConditionFailedException("Valor de juros deve ser maior que 0 e menor ou igual a 100");
        }
    }

    public void setMensalidadeJurosMulta(MensalidadeJurosMultaDTO mensalidadeBySerie) {
        this.setMulta(mensalidadeBySerie.getValorMulta());
        this.setJuros(mensalidadeBySerie.getValorJuros());
        this.setValorPagamento(mensalidadeBySerie.getValorMensalidade());
    }

}
