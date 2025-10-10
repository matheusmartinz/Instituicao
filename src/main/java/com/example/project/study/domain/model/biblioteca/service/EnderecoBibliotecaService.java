package com.example.project.study.domain.model.biblioteca.service;

import com.example.project.study.domain.model.biblioteca.dtos.EnderecoBibliotecaDTO;
import com.example.project.study.domain.model.biblioteca.models.EnderecoBiblioteca;
import com.example.project.study.domain.model.biblioteca.repository.EndereBiblioRepository;
import com.example.project.study.exceptions.FormatCEPInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoBibliotecaService {

    private final EndereBiblioRepository endereBiblioRepository;

    public EnderecoBibliotecaDTO createEnderecoBiblio(EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
        validateCEP(enderecoBibliotecaDTO);
        EnderecoBiblioteca enderecoBiblioteca = new EnderecoBiblioteca(enderecoBibliotecaDTO);
        EnderecoBiblioteca saveEnderecoBiblio = endereBiblioRepository.save(enderecoBiblioteca);
        return new EnderecoBibliotecaDTO(saveEnderecoBiblio);
    }

    public void validateCEP(EnderecoBibliotecaDTO enderecoBibliotecaDTO) {
        String cep = enderecoBibliotecaDTO.cep;

        if (cep == null || cep.length() != 8) {
            throw new FormatCEPInvalidException("CEP Inválido, Deve conter 8 caracteres");
        }
    }
}
