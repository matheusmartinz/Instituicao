package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;

import java.util.List;

public class Coordenador extends Pessoa {
    private List<Pessoa> professores;

    protected Coordenador(PessoaDTO pessoaDTO) {
        super(pessoaDTO);
    }
}
