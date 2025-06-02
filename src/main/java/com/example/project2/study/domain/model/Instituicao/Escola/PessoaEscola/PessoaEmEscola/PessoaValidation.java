package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaEmEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import org.springframework.stereotype.Component;

@Component
public class PessoaValidation {

    public void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("É necessário informar o nome do aluno.");
        }
    }

    public String validateCpf(String cpf) {
        if (cpf != null) {
            cpf = cpf
                    .replaceAll("\\.","")
                    .replaceAll("-", "");
            if (cpf.length() != 11) {
                throw new RuntimeException("Formato do CPF informado é inválido.");
            }
        } else {
            throw new RuntimeException("É necessário informar o CPF do aluno.");
        }
        return cpf;
    }

    public PessoaTelefoneDTO validateTelefone(PessoaTelefoneDTO telefone) {
        if (telefone != null) {
            telefone.ddd = telefone.ddd.replaceAll("[()]","");
            telefone.fone = telefone.fone.replaceAll("[\\s.-]", "").trim();
        }
        return telefone;
    }

    public void validateEmail(String email) {
        if (email == null) {
            throw new RuntimeException("É necessário informar o Email do aluno.");
        }
    }

    public void validateEndereco(EnderecoDTO endereco) {
        if (endereco == null) {
            throw new RuntimeException("É necessário informar o Endereço do aluno.");
        }
    }

    public void validatePessoa(String nome, String email, EnderecoDTO endereco) {
        validateNome(nome);
        validateEmail(email);
        validateEndereco(endereco);
    }
}
