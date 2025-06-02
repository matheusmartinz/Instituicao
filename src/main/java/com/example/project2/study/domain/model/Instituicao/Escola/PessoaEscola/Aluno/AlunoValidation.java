package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaEmEscola.PessoaValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoValidation {

    @Autowired
    private PessoaValidation pessoaValidation;

    public void validateAluno(AlunoDTO alunoDTO) {
        if (alunoDTO.serieAno == null) {
            throw new RuntimeException("É necessário informar a série do aluno.");
        }
        alunoDTO.cpf = pessoaValidation.validateCpf(alunoDTO.cpf);
        alunoDTO.telefone = pessoaValidation.validateTelefone(alunoDTO.telefone);
        pessoaValidation.validatePessoa(alunoDTO.nome, alunoDTO.email, alunoDTO.endereco);
    }

    public void validateSalas(List<Sala> salasComMesmaSerie, SerieAno serieAno) {
        if (salasComMesmaSerie == null || salasComMesmaSerie.isEmpty()) {
            throw new RuntimeException(String.format("Não possuimos salas para o %s.", serieAno.getDescritivo()));
        }
    }

    public void validateUuidAluno(Aluno aluno) {
        if(aluno == null) {
            throw new RuntimeException("Aluno não encontrado");
        }
    }
}
