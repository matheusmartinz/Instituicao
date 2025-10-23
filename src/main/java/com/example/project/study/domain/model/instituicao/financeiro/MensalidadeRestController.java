package com.example.project.study.domain.model.instituicao.financeiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<List<MensalidadeDTO>> findMensalidade() {
        return ResponseEntity.ok(mensalidadeService.findAll());
    }

    @GetMapping("/{uuidBoleto}")
    public ResponseEntity<MensalidadeDTO> findMensalidadeByUuid(@PathVariable UUID uuidBoleto) {
        return ResponseEntity.ok(mensalidadeService.findByUuid(uuidBoleto));
    }

    @DeleteMapping("/{uuidBoleto}")
    public ResponseEntity<String> deleteMensalidade(@PathVariable UUID uuidBoleto) {
        mensalidadeService.deleteBoleto(uuidBoleto);
        return ResponseEntity.ok("Mensalidade deletada com sucesso.");
    }

    @PutMapping("/{uuidBoleto}")
    public ResponseEntity<MensalidadeDTO> updateMensalidade(@RequestBody MensalidadeDTO mensalidadeDTO, @PathVariable UUID uuidBoleto) {
        MensalidadeDTO toReturnMensalidadeDTO = mensalidadeService.updateByUuid(mensalidadeDTO, uuidBoleto);
        return ResponseEntity.ok(toReturnMensalidadeDTO);
    }
}
