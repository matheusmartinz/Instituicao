package com.example.project.study.domain.model.instituicao.escola.sala.tarefa;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoRepository;
import com.example.project.study.domain.model.instituicao.escola.pessoa.aluno.AlunoValidation;
import com.example.project.study.domain.model.instituicao.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TarefaService extends EntidadeService<Tarefa> {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TarefaValidator tarefaValidator;

    @Autowired
    AlunoValidation alunoValidation;

    public TarefaDTO createTarefa(TarefaDTO tarefaDTO, UUID uuidAluno) {
        tarefaValidator.validateTarefas(tarefaDTO);
        Pessoa aluno = alunoRepository.findByUuid(uuidAluno);
        alunoValidation.validateAluno(aluno);
        Tarefa tarefa = new Tarefa(tarefaDTO);
        Tarefa saved = super.save(tarefa);
        return new TarefaDTO(saved);
    }

    @Override
    protected JpaRepository<Tarefa, Long> repository() {
        return tarefaRepository;
    }
}
