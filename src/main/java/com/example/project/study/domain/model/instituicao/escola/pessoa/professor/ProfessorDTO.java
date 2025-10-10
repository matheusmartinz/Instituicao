package com.example.project.study.domain.model.instituicao.escola.pessoa.professor;

import com.example.project.study.domain.model.instituicao.Disciplina;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.PessoaDTO;
import com.example.project.study.domain.model.instituicao.escola.PessoaTelefoneDTO;
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
