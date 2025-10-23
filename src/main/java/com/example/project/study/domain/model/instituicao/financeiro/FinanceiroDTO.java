package com.example.project.study.domain.model.instituicao.financeiro;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinanceiroDTO {
    private PagamentoDTO pagamentoDTO;
    private MensalidadeDTO mensalidadeDTO;

    public FinanceiroDTO(MensalidadeDTO mensalidadeDTO) {
        this.mensalidadeDTO = mensalidadeDTO;
    }

    public FinanceiroDTO(Pagamento pagamento) {
       this.pagamentoDTO = PagamentoDTO.of(pagamento);
    }

    public FinanceiroDTO() {}

    public static FinanceiroDTO of(MensalidadeDTO mensalidadeDTO) {
        return new FinanceiroDTO(mensalidadeDTO);
    }

    public static FinanceiroDTO of(Pagamento pagamento) {
        return new FinanceiroDTO(pagamento);
    }


}
