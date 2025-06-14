package com.example.project2.study.domain.model.Biblioteca.Service;

import com.example.project2.study.domain.model.Biblioteca.DTO.BibliotecaDTO;
import com.example.project2.study.domain.model.Biblioteca.Funcionario;
import com.example.project2.study.domain.model.Biblioteca.FuncionarioService;
import com.example.project2.study.domain.model.Biblioteca.Models.Biblioteca;
import com.example.project2.study.domain.model.Biblioteca.Repository.BibliotecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
