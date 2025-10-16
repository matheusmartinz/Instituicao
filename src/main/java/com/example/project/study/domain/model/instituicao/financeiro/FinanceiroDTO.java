package com.example.project.study.domain.model.instituicao.financeiro;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class FinanceiroDTO extends EntidadeIdUUIDDTO{
    private PagamentoDTO pagamentoDTO;
    private MensalidadeDTO mensalidadeDTO;

}
