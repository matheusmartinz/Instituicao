package com.example.project.study.dataproviders;

import com.example.project.study.domain.model.instituicao.escola.PessoaTelefoneDTO;

public class PessoaTelefoneDTODataProvider {
    public static PessoaTelefoneDTO ofTelefone() {
        PessoaTelefoneDTO pessoaTelefoneDTO = new PessoaTelefoneDTO();
        pessoaTelefoneDTO.ddd = "(44)";
        pessoaTelefoneDTO.fone = "9 9.222-8899";
        return pessoaTelefoneDTO;
    }
}
