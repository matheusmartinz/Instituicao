package com.example.project2.study.domain.model.Instituicao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")

public class statusController {
    @GetMapping
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Tá funcionando");
    }
}
