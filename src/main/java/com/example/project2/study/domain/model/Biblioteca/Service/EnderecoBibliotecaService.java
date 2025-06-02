package com.example.project2.study.domain.model.Biblioteca.Service;

import com.example.project2.study.domain.model.Biblioteca.DTO.EnderecoBibliotecaDTO;
import com.example.project2.study.domain.model.Biblioteca.Models.EnderecoBiblioteca;
import com.example.project2.study.domain.model.Biblioteca.Repository.EndereBiblioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoBibliotecaService {

        @Autowired
        EndereBiblioRepository endereBiblioRepository;

    public EnderecoBibliotecaDTO createEnderecoBiblio(EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
        validateCEP(enderecoBibliotecaDTO);
            EnderecoBiblioteca enderecoBiblioteca = new EnderecoBiblioteca(enderecoBibliotecaDTO);
            EnderecoBiblioteca saveEnderecoBiblio = endereBiblioRepository.save(enderecoBiblioteca);
            return new EnderecoBibliotecaDTO(saveEnderecoBiblio);
    }

    public EnderecoBibliotecaDTO validateCEP(EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
        String cep = enderecoBibliotecaDTO.cep;

        if (cep == null || cep.length() != 8) {
            throw new RuntimeException("CEP Inválido, Deve conter 8 caracteres");
        }
        return enderecoBibliotecaDTO;
    }
}
