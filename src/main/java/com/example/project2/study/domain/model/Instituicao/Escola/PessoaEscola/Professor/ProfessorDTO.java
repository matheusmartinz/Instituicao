package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorDTO extends PessoaDTO {
        private String quantidadeAulas;
        private List<Disciplina> disciplinas;


    private static ProfessorDTO of(Pessoa professor) {
        ProfessorDTO toReturn = new ProfessorDTO();
        toReturn.quantidadeAulas = professor.getQuantidadeAulas();
        toReturn.disciplinas = professor.getDisciplinas();
        toReturn.cpf = professor.getCpf();
        toReturn.nome = professor.getNome();
        toReturn.email = professor.getEmail();
        toReturn.telefone = new PessoaTelefoneDTO(professor.getTelefone());
        toReturn.endereco = EnderecoDTO.of(professor.getEndereco());

        return toReturn;
    }

    public static List<ProfessorDTO> listOf(List<Pessoa> professores) {
        return professores.stream().map(ProfessorDTO::of).toList();
    }
}
