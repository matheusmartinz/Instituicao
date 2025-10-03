package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaEmEscola.PessoaValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlunoValidation {
    private final PessoaValidation pessoaValidation;

    public void validateAluno(AlunoDTO alunoDTO) {
        if (alunoDTO.getSerieAno() == null) {
            throw new RuntimeException("É necessário informar a série do aluno.");
        }
        alunoDTO.alterarCpf(pessoaValidation.validateCpf(alunoDTO.getCpf()));
        alunoDTO.setTelefone(pessoaValidation.validateTelefone(alunoDTO.getTelefone()));
        pessoaValidation.validatePessoa(alunoDTO.getNome(), alunoDTO.getEmail(), alunoDTO.getEndereco());
    }

    public void validateSalas(List<Sala> salasComMesmaSerie, SerieAno serieAno) {
        if (salasComMesmaSerie == null || salasComMesmaSerie.isEmpty()) {
            throw new RuntimeException(String.format("Não possuimos salas para o %s.", serieAno.getDescritivo()));
        }
    }

    public void validateAluno(Pessoa pessoa) {
        if (pessoa == null) {
            throw new RuntimeException("Aluno não encontrado");
        }
    }
}
