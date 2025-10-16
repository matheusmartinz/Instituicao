package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.exceptions.ConditionFailedException;
import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class MensalidadeValidation {

    public void validateBoletoCreated(MensalidadeDTO mensalidadeDTO, Pessoa aluno) {
        if (aluno.isBolsista() && !mensalidadeDTO.getStatusPagamento().isIsento()) {
            mensalidadeDTO.setStatusPagamento(StatusPagamento.ISENTO);
            mensalidadeDTO.setValorPago(mensalidadeDTO.getValorPagamento());
        }
        if (mensalidadeDTO.getValorPagamento().compareTo(BigDecimal.ZERO) == 0) {
            throw new ConditionFailedException("Erro no valor gerado do boleto.");
        }
    }

    public void validatePagarBoleto(Mensalidade mensalidade, MensalidadeDTO mensalidadeDTO) {
        if (LocalDate.now().isAfter(mensalidade.getDataVencimento())) {
            mensalidadeDTO.setStatusPagamento(StatusPagamento.ATRASADO);
        }
    }

    public void checkIsNull(Mensalidade mensalidadeFounded) {
        if (mensalidadeFounded == null) {
            throw new EntidadeNaoEncontradaException("Mensalidade deste aluno não encontrada.");
        }
    }

    public void isPago(Mensalidade mensalidade) {
        if (mensalidade.isPago()) {
            throw new ConditionFailedException("Mensalidade já paga.");
        }
    }

    public void  isVencido(MensalidadeDTO mensalidadeDTO, Mensalidade mensalidade) {
        if (mensalidade.isVencido()) {
            mensalidadeDTO.setValorPagamento(mensalidade.updateValorPagamento(mensalidadeDTO));
        }
    }

    public void isEmDia(MensalidadeDTO mensalidadeDTO, Mensalidade mensalidade) {
        if (mensalidade.isEmDia()){
            mensalidadeDTO.setValorPagamento(mensalidade.updateValorPagamentoEmDia(mensalidadeDTO));
        }
    }
}
