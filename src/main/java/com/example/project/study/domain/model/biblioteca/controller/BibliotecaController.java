package com.example.project.study.domain.model.biblioteca.controller;

import com.example.project.study.domain.model.biblioteca.dtos.BibliotecaDTO;
import com.example.project.study.domain.model.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @PostMapping
    public ResponseEntity<BibliotecaDTO> createBiblioteca(@RequestBody BibliotecaDTO bibliotecaDTO) {
        BibliotecaDTO saveBiblioteca = bibliotecaService.createBiblioteca(bibliotecaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBiblioteca);
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaDTO>> listaBiblioteca() {
        List<BibliotecaDTO> getAll = bibliotecaService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(getAll);
    }
}
