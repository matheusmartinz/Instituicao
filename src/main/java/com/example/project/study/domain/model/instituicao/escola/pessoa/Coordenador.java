package com.example.project.study.domain.model.instituicao.escola.pessoa;

import com.example.project.study.domain.model.instituicao.escola.PessoaDTO;

import java.util.List;

public class Coordenador extends Pessoa {
    private List<Pessoa> professores;

    protected Coordenador(PessoaDTO pessoaDTO) {
        super(pessoaDTO);
    }
}
