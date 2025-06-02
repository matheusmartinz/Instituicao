package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.Professor;

import java.util.List;

public class Coordenador extends Pessoa {
    private List<Professor> professores;

    protected Coordenador(PessoaDTO pessoaDTO) {
        super(pessoaDTO);
    }
}
