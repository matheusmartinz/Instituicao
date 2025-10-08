package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MensalidateValidation {

    public void validateBoletoCreated(MensalidadeDTO mensalidadeDTO, Pessoa aluno) {
        if (aluno.isBolsista() && !mensalidadeDTO.getStatusPagamento().isIsento()) {
            mensalidadeDTO.setStatusPagamento(StatusPagamento.ISENTO);
            mensalidadeDTO.setValorPago(mensalidadeDTO.getValorPagamento());
        }
        if (mensalidadeDTO.getValorPagamento().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Erro no valor gerado do boleto");
        }
//        mensalidadeDTO.getValorPagamento
    }
}
