package com.example.project2.study.domain.model.Biblioteca.Controller;

import com.example.project2.study.domain.model.Biblioteca.DTO.LivroDTO;
import com.example.project2.study.domain.model.Biblioteca.Service.LivroService;
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
