package com.example.project2.study.domain.model.Biblioteca.Service;

import com.example.project2.study.domain.model.Biblioteca.DTO.LivroDTO;
import com.example.project2.study.domain.model.Biblioteca.Models.Livro;
import com.example.project2.study.domain.model.Biblioteca.Repository.LivroRepository;
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
