package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;

import java.util.List;

public class Diretor extends Pessoa {
    private List<Pessoa> pessoas;

    protected Diretor(PessoaDTO pessoaDTO) {
        super(pessoaDTO);
    }
}
