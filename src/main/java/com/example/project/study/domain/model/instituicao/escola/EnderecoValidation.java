package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.exceptions.NullFieldException;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidation {

    public void validateCidadeAndEstadoAndCEP(EnderecoDTO endereco) {
        if (endereco.getCidade() == null || endereco.getCidade().isBlank()) {
            throw new NullFieldException("Favor informar a cidade.");
        }
        if (endereco.getCep() == null || endereco.getCep().isBlank() || endereco.getCep().length() < 8) {
            throw new NullFieldException("Favor informar o cep.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isBlank()) {
            throw new NullFieldException("Favor informar o estado.");
        }
    }

    public void validateEnderecoDTO(EscolaDTO escolaDTO) {
        if (escolaDTO.getEndereco() == null) {
            throw new NullFieldException("Favor informar o endereço.");
        }
    }
}
