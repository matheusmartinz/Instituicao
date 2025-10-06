package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor.ProfessorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class PessoaDTO {
    @Setter
    protected String nome;
    protected String cpf;
    @Setter
    protected String email;
    @Setter
    protected PessoaTelefoneDTO telefone;
    @Setter
    protected EnderecoDTO endereco;
    protected UUID uuid;

    public PessoaDTO(AlunoDTO alunoDTO) {
        this.nome = alunoDTO.getNome();
        this.cpf = alunoDTO.getCpf();
        this.email = alunoDTO.getEmail();
        this.telefone = alunoDTO.getTelefone();
        this.endereco = alunoDTO.getEndereco();
        this.uuid = alunoDTO.getUuid();
    }

    public PessoaDTO(ProfessorDTO professorDTO) {
        this.nome = professorDTO.getNome();
        this.cpf = professorDTO.getCpf();
        this.email = professorDTO.getEmail();
        this.telefone = professorDTO.getTelefone();
        this.endereco = professorDTO.getEndereco();
        this.uuid = professorDTO.getUuid();
    }


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
        return new PessoaDTO(alunoDTO);
    }

    public static PessoaDTO of(ProfessorDTO professorDTO) {
        return new PessoaDTO(professorDTO);
    }

    public void alterarCpf(String cpf) {
        this.cpf = cpf;
    }
}
