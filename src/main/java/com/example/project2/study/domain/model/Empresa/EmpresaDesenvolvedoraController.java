package com.example.project2.study.domain.model.Empresa;

import com.example.project2.study.domain.Repositories.EmpresaDesenvolvedoraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa-desenvolvedora")
public class EmpresaDesenvolvedoraController {

    @Autowired
    EmpresaDesenvolvedoraService empresaDesenvolvedoraService;

    @PostMapping
    public ResponseEntity<EmpresaDesenvolvedoraDTO> criarEmpresa(@RequestBody @Valid EmpresaDesenvolvedoraDTO empresaDesenvolvedoraDTO) {
        EmpresaDesenvolvedoraDTO toReturn = empresaDesenvolvedoraService.createEmpresaDesenvolvedora(empresaDesenvolvedoraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
    }
}
