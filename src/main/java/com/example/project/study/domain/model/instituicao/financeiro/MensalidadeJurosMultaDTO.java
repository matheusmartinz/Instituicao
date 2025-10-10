package com.example.project.study.domain.model.instituicao.financeiro;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MensalidadeJurosMultaDTO {
    private final BigDecimal valorMensalidade;
    private final BigDecimal valorMulta;
    private final BigDecimal valorJuros;

    private MensalidadeJurosMultaDTO(double valorMensalidade,
                                     double valorMulta,
                                     double valorJuros) {
        this.valorMensalidade = BigDecimal.valueOf(valorMensalidade);
        this.valorMulta = BigDecimal.valueOf(valorMulta);
        this.valorJuros = BigDecimal.valueOf(valorJuros);
    }

    public static MensalidadeJurosMultaDTO of(double valorMensalidade,
                                              double valorMulta,
                                              double valorJuros) {
        return new MensalidadeJurosMultaDTO(valorMensalidade, valorMulta, valorJuros);
    }
}
