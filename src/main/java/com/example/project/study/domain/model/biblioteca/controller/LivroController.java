package com.example.project.study.domain.model.biblioteca.controller;

import com.example.project.study.domain.model.biblioteca.dtos.LivroDTO;
import com.example.project.study.domain.model.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livroBiblioteca")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroDTO> createLivro(@RequestBody LivroDTO livroDTO) {
        LivroDTO createLivros = livroService.createLivros(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createLivros);
    }
}
