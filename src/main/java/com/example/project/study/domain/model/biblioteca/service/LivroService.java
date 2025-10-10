package com.example.project.study.domain.model.biblioteca.service;

import com.example.project.study.domain.model.biblioteca.dtos.LivroDTO;
import com.example.project.study.domain.model.biblioteca.models.Livro;
import com.example.project.study.domain.model.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;


    public LivroDTO createLivros(LivroDTO livroDTO) {
        Livro livro = new Livro(livroDTO);
        Livro saveLivro = livroRepository.save(livro);
        return new LivroDTO(saveLivro);
    }
}
