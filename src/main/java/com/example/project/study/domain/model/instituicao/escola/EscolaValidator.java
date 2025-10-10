package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import com.example.project.study.exceptions.NullFieldException;
import org.springframework.stereotype.Component;

@Component
public class EscolaValidator {

    public void validateDTO(EscolaDTO escolaDTO) {
        if (escolaDTO.getNome() == null || escolaDTO.getNome().isBlank()) {
            throw new NullFieldException("Favor informar o nome.");
        } else {
            escolaDTO.setNome(escolaDTO.getNome().trim());
        }
    }

    public void validaEscola(Escola escola) {
        if (escola == null || escola.getUuid() == null || escola.getUuid().toString().isBlank()) {
            throw new EntidadeNaoEncontradaException("Escola não encontrada.");
        }
    }
}
