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

    @PostMapping("/{uuidAluno}")
    public ResponseEntity<MensalidadeDTO> pagamento(@PathVariable UUID uuidAluno, @RequestBody MensalidadeDTO mensalidadeDTO) {
        MensalidadeDTO mensalidadeDTOConsultada = mensalidadeService.consultaMensalidade(mensalidadeDTO, uuidAluno);
        MensalidadeDTO mensalidadeDTOPaga = mensalidadeService.pagarBoleto(mensalidadeDTOConsultada, uuidAluno);
        return ResponseEntity.ok(mensalidadeDTOPaga);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> createPagamento(@RequestBody MensalidadeDTO mensalidadeDTO, @PathVariable UUID uuidAluno, @RequestBody PagamentoDTO pagamentoDTO){
        MensalidadeDTO mensalidadeConsultada = mensalidadeService.consultaMensalidade(mensalidadeDTO, uuidAluno);
        PagamentoDTO pagamentoDTORealizado = pagamentoService.pagarMensalidade(mensalidadeConsultada, uuidAluno, pagamentoDTO);
        return ResponseEntity.ok().body(pagamentoDTORealizado);
    }
}
