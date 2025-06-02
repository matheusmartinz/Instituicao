package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorDTO extends PessoaDTO {
        public String quantidadeAulas;
        public List<Disciplina> disciplinas;


    private static ProfessorDTO of(Professor professor) {
        ProfessorDTO toReturn = new ProfessorDTO();
        toReturn.quantidadeAulas = professor.getQuantidadeAulas();
        toReturn.disciplinas = professor.getDisciplinas();
        toReturn.cpf = professor.getCpf();
        toReturn.nome = professor.getNome();
        toReturn.email = professor.getEmail();
        toReturn.telefone = new PessoaTelefoneDTO(professor.getTelefone());
        toReturn.endereco = new EnderecoDTO(professor.getEndereco());

        return toReturn;
    }

    public static List<ProfessorDTO> listOf(List<Professor> professores) {
        return professores.stream().map(ProfessorDTO::of).toList();
    }
}
