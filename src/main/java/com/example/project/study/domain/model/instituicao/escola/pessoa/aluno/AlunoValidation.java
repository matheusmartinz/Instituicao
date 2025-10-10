package com.example.project.study.domain.model.instituicao.escola.pessoa.aluno;

import com.example.project.study.exceptions.ConditionFailedException;
import com.example.project.study.exceptions.EntidadeNaoEncontradaException;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.sala.Sala;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaValidation;
import com.example.project.study.domain.model.instituicao.escola.SerieAno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlunoValidation {
    private final PessoaValidation pessoaValidation;

    public void validateAluno(AlunoDTO alunoDTO) {
        if (alunoDTO.getSerieAno() == null) {
            throw new ConditionFailedException("É necessário informar a série do aluno.");
        }
        alunoDTO.alterarCpf(pessoaValidation.validateCpf(alunoDTO.getCpf()));
        alunoDTO.setTelefone(pessoaValidation.validateTelefone(alunoDTO.getTelefone()));
        pessoaValidation.validatePessoa(alunoDTO.getNome(), alunoDTO.getEmail(), alunoDTO.getEndereco());
    }

    public void validateSalas(List<Sala> salasComMesmaSerie, SerieAno serieAno) {
        if (salasComMesmaSerie == null || salasComMesmaSerie.isEmpty()) {
            throw new ConditionFailedException(String.format("Não possuimos salas para o %s.", serieAno.getDescritivo()));
        }
    }

    public void validateAluno(Pessoa pessoa) {
        if (pessoa == null) {
            throw new EntidadeNaoEncontradaException("Aluno não encontrado");
        }
    }
}
