package com.example.project2.study.DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;

public class EnderecoDTODataProvider {

    public static EnderecoDTO ofMaringa() {
        return EnderecoDTO.of("Maringá", "87060-550", "PR");
    }
}
