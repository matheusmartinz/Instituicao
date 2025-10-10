package com.example.project.study.domain.model.instituicao.escola.pessoa.professor;

import com.example.project.study.domain.model.instituicao.Disciplina;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoDTO;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.CargaHorariaValidator;
import com.example.project.study.domain.model.instituicao.escola.pessoa.PessoaRepository;
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
