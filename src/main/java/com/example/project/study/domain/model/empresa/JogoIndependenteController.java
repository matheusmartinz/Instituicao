package com.example.project.study.domain.model.empresa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/independente-jogo")
@RestController
public class JogoIndependenteController {

    @Autowired
    private JogoIndependenteService jogoIndependenteService;

        @PostMapping
        public ResponseEntity<JogoIndependenteDTO> createJogoIndependente(@RequestBody @Valid JogoIndependenteDTO jogoIndependenteDTO) {
          JogoIndependenteDTO toReturn = jogoIndependenteService.createJogoIndependente(jogoIndependenteDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
        }

        @GetMapping
        public ResponseEntity<List<JogoIndependenteDTO>> listaJogoIndependente(JogoIndependenteDTO jogoIndependenteDTO) {
            List<JogoIndependenteDTO> listJogoIndependente = jogoIndependenteService.findAll();
            return ResponseEntity.status(HttpStatus.FOUND).body(listJogoIndependente);
        }
}
