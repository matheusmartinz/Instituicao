package com.example.project2.study.domain.model.Instituicao.Escola;

import org.springframework.stereotype.Component;

@Component
public class EscolaValidator {

    public void validateDTO(EscolaDTO escolaDTO) {
        if (escolaDTO.getNome() == null || escolaDTO.getNome().isBlank()) {
            throw new RuntimeException("Favor informar o nome.");
        } else {
            escolaDTO.setNome(escolaDTO.getNome().trim());
        }
    }

    public void validaEscola(Escola escola) {
        if (escola == null || escola.getUuid() == null || escola.getUuid().toString().isBlank()) {
            throw new RuntimeException("Escola não encontrada.");
        }
    }
}
