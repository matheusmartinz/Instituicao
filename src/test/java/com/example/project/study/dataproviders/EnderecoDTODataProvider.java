package com.example.project.study.dataproviders;

import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;

public class EnderecoDTODataProvider {

    public static EnderecoDTO ofMaringa() {
        return EnderecoDTO.of("Maringá", "87060-550", "PR");
    }
}
