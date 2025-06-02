package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidation {

    public void validateCidadeAndEstadoAndCEP(EnderecoDTO endereco) {
        if (endereco.cidade == null || endereco.cidade.isBlank()) {
            throw new RuntimeException("Favor informar a cidade.");
        }
        if (endereco.cep == null || endereco.cep.isBlank() || endereco.cep.length() <8) {
            throw new RuntimeException("Favor informar o cep.");
        }
        if (endereco.estado == null || endereco.estado.isBlank()) {
            throw new RuntimeException("Favor informar o estado.");
        }
    }

    public void validateEnderecoDTO(EscolaDTO escolaDTO) {
        if (escolaDTO.endereco == null) {
            throw new RuntimeException("Favor informar o endereço.");
        }
    }
}
