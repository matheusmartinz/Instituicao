package com.example.project.study.domain.model.instituicao.escola.sala;

import com.example.project.study.api.SuperRestController;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoService;
import lombok.RequiredArgsConstructor;
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
    private final AlunoService alunoService;

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

    @PutMapping("/{uuidSala}")
    public ResponseEntity<SalaDTO> updateSala(@RequestBody SalaDTO salaDTO, @PathVariable UUID uuidSala){
        SalaDTO toUpdate = salaService.updateSalaByUuid(salaDTO, uuidSala);
        return ResponseEntity.status(HttpStatus.OK).body(toUpdate);
    }

    @DeleteMapping("/{uuidSala}")
    public ResponseEntity<String> deleteSala(@PathVariable UUID uuidSala){
        salaService.deleteSala(uuidSala);
        return ResponseEntity.ok("Sala deletada com sucesso!");
    }
}
