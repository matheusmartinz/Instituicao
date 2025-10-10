package com.example.project.study.dataproviders;

import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.EscolaDTO;

public class EscolaDTODataProvider {

        public static EscolaDTO createEscolaDTO(String nome, EnderecoDTO enderecoDTO ) {
            EscolaDTO escolaDTO = new EscolaDTO();
            escolaDTO.setNome(nome);
            escolaDTO.setEndereco(enderecoDTO);
            return escolaDTO;
        }
}
