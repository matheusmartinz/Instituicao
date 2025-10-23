package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/pagamento")
@RestController
public class PagamentoRestController {

    @Autowired
    private FinanceiroService financeiroService;

    @GetMapping
    public ResponseEntity<Void> getAllPagamentos() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{uuidMensalidade}")
    public ResponseEntity<PagamentoDTO> pagamento(@PathVariable UUID uuidMensalidade, @RequestBody PagamentoDTO pagamentoDTO) {
        return ResponseEntity.ok().body(financeiroService.pagarMensalidade(pagamentoDTO, uuidMensalidade));
    }

//    @PostMapping
//    public ResponseEntity<FinanceiroDTO> createPagamento(@RequestBody FinanceiroDTO financeiroDTO) {
//        FinanceiroDTO toReturnPagamento = financeiroService.pagarMensalidade(financeiroDTO);
//        return ResponseEntity.ok().body(toReturnPagamento);
//    }

    @DeleteMapping("/{uuidPagamento}")
    public ResponseEntity<Void> deletePagamento(@PathVariable UUID uuidPagamento) {
        financeiroService.deletePagamento(uuidPagamento);
        return ResponseEntity.ok().build();
    }
}
