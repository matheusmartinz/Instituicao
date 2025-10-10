package com.example.project.study.domain.model.biblioteca.controller;

import com.example.project.study.domain.model.biblioteca.dtos.EnderecoBibliotecaDTO;
import com.example.project.study.domain.model.biblioteca.service.EnderecoBibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecoBiblioteca")
public class EnderecoBibliotecaController {

    @Autowired
    EnderecoBibliotecaService enderecoBibliotecaService;

    @PostMapping
    public ResponseEntity<EnderecoBibliotecaDTO> createEndereco(@RequestBody EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
            EnderecoBibliotecaDTO createdEnderecoBiblioteca = enderecoBibliotecaService.createEnderecoBiblio(enderecoBibliotecaDTO);
            return ResponseEntity.ok(createdEnderecoBiblioteca);
    }
}
