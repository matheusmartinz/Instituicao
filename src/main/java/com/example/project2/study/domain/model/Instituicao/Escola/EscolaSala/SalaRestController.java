package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala;

import com.example.project2.study.Api.SuperRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sala")

public class SalaRestController extends SuperRestController {
    private final SalaService salaService;

    @PostMapping("/{escolaUUID}")
    public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO, @PathVariable UUID escolaUUID) {
        SalaDTO toReturn = salaService.createSala(salaDTO, escolaUUID);
        return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
    }

    @GetMapping
    public ResponseEntity<List<SalaDataGridDTO>> getSalas() {
        List<SalaDataGridDTO> salaDataGridDTO = salaService.getSalas();
        return ResponseEntity.status(HttpStatus.OK).body(salaDataGridDTO);
    }
}
