package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/pagamento")
@RestController
public class PagamentoRestController {

    @Autowired
    private AlunoValidation alunoValidation;
    @Autowired
    private MensalidadeService mensalidadeService;

    @PostMapping("/{uuidAluno}")
    public ResponseEntity<String> pagamento(@PathVariable UUID uuidAluno, MensalidadeDTO mensalidadeDTO) {
        mensalidadeService.consultaBoleto(mensalidadeDTO, uuidAluno);
        mensalidadeService.pagarBoleto(mensalidadeDTO, uuidAluno);
        return ResponseEntity.ok().body("Pagamento realizado com sucesso.");
    }
}
