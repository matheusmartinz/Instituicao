package com.example.project2.study.DataProviders;

import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;

public class PessoaTelefoneDTODataProvider {
    public static PessoaTelefoneDTO ofTelefone() {
        PessoaTelefoneDTO pessoaTelefoneDTO = new PessoaTelefoneDTO();
        pessoaTelefoneDTO.ddd = "(44)";
        pessoaTelefoneDTO.fone = "9 9.222-8899";
        return pessoaTelefoneDTO;
    }
}
