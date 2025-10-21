package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.exceptions.ConditionFailedException;
import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Component;

@Component
public class PagamentoValidation {
    public void checkIsNull(PagamentoDTO pagamentoDTO) {
        if(pagamentoDTO == null){
            throw new EntidadeNaoEncontradaException("Obrigatório informar pagamento.");
        }
        if (pagamentoDTO.getMensalidadeFK() == null) {
            throw new ConditionFailedException("Obrigatório informar mensalidade a ser paga.");
        }
    }
}
