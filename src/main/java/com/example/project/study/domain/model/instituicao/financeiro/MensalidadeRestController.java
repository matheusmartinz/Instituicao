package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mensalidade")
public class MensalidadeRestController {

    @Autowired
    private MensalidadeService mensalidadeService;

    @PostMapping("/{uuidAluno}")
    public ResponseEntity<MensalidadeDTO> createMensalidade(@PathVariable UUID uuidAluno) {
        MensalidadeDTO toReturnCreateBoleto = mensalidadeService.createBoleto(uuidAluno);
        return ResponseEntity.ok(toReturnCreateBoleto);
    }
}
