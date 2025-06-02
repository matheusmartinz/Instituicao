package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sala/{escolaUUID}")

public class SalaRestController {
    private final SalaService salaService;

    @PostMapping
    public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO, @PathVariable UUID escolaUUID) {
        SalaDTO toReturn = salaService.createSala(salaDTO, escolaUUID);
        return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
    }
}
