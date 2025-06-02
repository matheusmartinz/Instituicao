package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarefa")
public class TarefaRestController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping("/{uuidAluno}")
    public ResponseEntity<TarefaDTO> createTarefa(@RequestBody TarefaDTO tarefaDTO, @PathVariable UUID uuidAluno) {
        TarefaDTO createTarefa = tarefaService.createTarefa(tarefaDTO,uuidAluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTarefa);
    }
}
