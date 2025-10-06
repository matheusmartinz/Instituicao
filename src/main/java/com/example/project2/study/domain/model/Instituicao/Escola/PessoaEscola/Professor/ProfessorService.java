package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Professor;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.CargaHorariaValidator;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProfessorService {
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    private AlunoValidation alunoValidation;
    @Autowired
    private CargaHorariaValidator cargaHorariaValidator;

    public void addDisciplinaAluno(AlunoDTO alunoDTO, Disciplina disciplina) {
        alunoValidation.validateAluno(alunoDTO);
        Pessoa alunoByUuid = pessoaRepository.findByUuid(alunoDTO.getUuid());
        alunoValidation.validateAluno(alunoByUuid);

        if (!alunoByUuid.getDisciplinas().contains(disciplina)) {
            alunoByUuid.updateDisciplina(disciplina);
            cargaHorariaValidator.validateCargaHoraria(alunoByUuid.getCargaHoraria());
            pessoaRepository.save(alunoByUuid);
        }
    }
}
