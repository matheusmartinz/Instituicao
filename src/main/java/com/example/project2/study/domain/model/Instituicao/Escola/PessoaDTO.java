package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.ProfessorDTO;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class PessoaDTO {
    public String nome;
    public String cpf;
    public String email;
    public PessoaTelefoneDTO telefone;
    public EnderecoDTO endereco;
    public UUID uuid;


    public static List<PessoaDTO> listOfPessoaDTO(List<Pessoa> pessoas) {
        return pessoas.stream().map(PessoaDTO::new).toList();
    }

    private PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.telefone = new PessoaTelefoneDTO(pessoa.getTelefone());
        this.cpf = pessoa.getCpf();
        this.endereco = EnderecoDTO.of(pessoa.getEndereco());
        this.email = pessoa.getEmail();
        this.uuid = pessoa.getUuid();
    }

    public static PessoaDTO of(AlunoDTO alunoDTO) {
        PessoaDTO toReturn = new PessoaDTO();
        toReturn.nome = alunoDTO.nome;
        toReturn.cpf = alunoDTO.cpf;
        toReturn.email = alunoDTO.email;
        toReturn.telefone = alunoDTO.telefone;
        toReturn.endereco = alunoDTO.endereco;
        toReturn.uuid = alunoDTO.uuid;
        return toReturn;

    }

    public static PessoaDTO of(ProfessorDTO professorDTO) {
        PessoaDTO toReturn = new PessoaDTO();
        toReturn.nome = professorDTO.nome;
        toReturn.cpf = professorDTO.cpf;
        toReturn.email = professorDTO.email;
        toReturn.telefone = professorDTO.telefone;
        toReturn.endereco = professorDTO.endereco;
        toReturn.uuid = professorDTO.uuid;
        return toReturn;
    }
}
