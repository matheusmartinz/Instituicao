package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidation {

    public void validateCidadeAndEstadoAndCEP(EnderecoDTO endereco) {
        if (endereco.getCidade() == null || endereco.getCidade().isBlank()) {
            throw new RuntimeException("Favor informar a cidade.");
        }
        if (endereco.getCep() == null || endereco.getCep().isBlank() || endereco.getCep().length() < 8) {
            throw new RuntimeException("Favor informar o cep.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isBlank()) {
            throw new RuntimeException("Favor informar o estado.");
        }
    }

    public void validateEnderecoDTO(EscolaDTO escolaDTO) {
        if (escolaDTO.getEndereco() == null) {
            throw new RuntimeException("Favor informar o endereço.");
        }
    }
}
