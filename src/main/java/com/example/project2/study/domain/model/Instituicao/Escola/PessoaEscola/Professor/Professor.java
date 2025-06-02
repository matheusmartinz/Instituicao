package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaDTO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Professor extends Pessoa {
    private String quantidadeAulas;
    private List<Disciplina> disciplinas;

    protected Professor() {}

    public static List<Professor> listOf(List<ProfessorDTO> professores) {
        return professores.stream().map(Professor::new).toList();
    }

    private Professor(ProfessorDTO professorDTO) {
        super(PessoaDTO.of(professorDTO));
        this.setQuantidadeAulas(professorDTO.getQuantidadeAulas());
        this.setDisciplinas(professorDTO.getDisciplinas());
        this.setTelefone(new PessoaTelefone(professorDTO.telefone));
        this.setEndereco(new Endereco(professorDTO.endereco));
    }
}
