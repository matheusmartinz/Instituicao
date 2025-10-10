package com.example.project.study.domain.model.instituicao.escola.pessoa;

import com.example.project.study.domain.model.instituicao.escola.PessoaDTO;

import java.util.List;

public class Diretor extends Pessoa {
    private List<Pessoa> pessoas;

    protected Diretor(PessoaDTO pessoaDTO) {
        super(pessoaDTO);
    }
}
