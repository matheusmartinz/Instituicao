package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/pagamento")
@RestController
public class PagamentoRestController {

    @Autowired
    private MensalidadeService mensalidadeService;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private FinanceiroService financeiroService;

    @PostMapping("/{uuidAluno}")
    public ResponseEntity<MensalidadeDTO> pagamento(@PathVariable UUID uuidAluno, @RequestBody MensalidadeDTO mensalidadeDTO) {
        MensalidadeDTO mensalidadeDTOConsultada = mensalidadeService.consultaMensalidade(mensalidadeDTO, uuidAluno);
        MensalidadeDTO mensalidadeDTOPaga = mensalidadeService.pagarBoleto(mensalidadeDTOConsultada, uuidAluno);
        return ResponseEntity.ok(mensalidadeDTOPaga);
    }

    @PostMapping("/testepagamento/{uuidAluno}")
    public ResponseEntity<FinanceiroDTO> createPagamento(@RequestBody FinanceiroDTO financeiroDTO){
        FinanceiroDTO toReturnPagamento = financeiroService.pagarMensalidade(financeiroDTO);
        return ResponseEntity.ok().body(toReturnPagamento);
    }
}
