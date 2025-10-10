package com.example.project.study.domain.model.instituicao.escola.pessoa.aluno;

import com.example.project.study.api.SuperRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aluno")

public class AlunoRestController extends SuperRestController {
    private final AlunoService alunoService;

    @PostMapping("/{uuidEscola}")
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO,
                                                @PathVariable UUID uuidEscola) {
        AlunoDTO toReturn = alunoService.createAluno(alunoDTO, uuidEscola);
        return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDataGridDTO>> findAll() {
        List<AlunoDataGridDTO> listarAlunos = alunoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listarAlunos);
    }

    @PutMapping
    public ResponseEntity<AlunoDataGridDTO> updateAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDataGridDTO updateAlunos = alunoService.updateByUuid(alunoDTO);
        return ResponseEntity.ok(updateAlunos);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID uuid) {
        alunoService.deleteAluno(uuid);
        return ResponseEntity.noContent().build();
    }
}
