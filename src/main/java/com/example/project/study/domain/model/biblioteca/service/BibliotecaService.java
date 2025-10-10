package com.example.project.study.domain.model.biblioteca.service;

import com.example.project.study.domain.model.biblioteca.dtos.BibliotecaDTO;
import com.example.project.study.domain.model.biblioteca.Funcionario;
import com.example.project.study.domain.model.biblioteca.FuncionarioService;
import com.example.project.study.domain.model.biblioteca.models.Biblioteca;
import com.example.project.study.domain.model.biblioteca.repository.BibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private BibliotecaRepository bibliotecaRepository;
    private FuncionarioService funcionarioService;

    public BibliotecaDTO createBiblioteca(BibliotecaDTO bibliotecaDTO) {
        Biblioteca createBiblioteca = new Biblioteca(bibliotecaDTO);
        List<Funcionario> funcionario = funcionarioService.saveFuncionarios(createBiblioteca.getFuncionarios());
        createBiblioteca.setFuncionarios(funcionario);
        Biblioteca saveBiblioteca = bibliotecaRepository.save(createBiblioteca);
        return new BibliotecaDTO(saveBiblioteca);
    }

    public List<BibliotecaDTO> findAll() {
        return bibliotecaRepository.findAll().stream().map(BibliotecaDTO::new).toList();
    }
}
