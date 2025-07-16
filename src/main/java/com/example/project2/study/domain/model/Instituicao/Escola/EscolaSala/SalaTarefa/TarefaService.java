package com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaTarefa;

import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.Aluno;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno.AlunoValidation;
import com.example.project2.study.domain.model.Instituicao.Tarefa;
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

    public TarefaDTO createTarefa(TarefaDTO tarefaDTO, UUID uuidAluno){
        tarefaValidator.validateTarefas(tarefaDTO);
        Aluno aluno = alunoRepository.findByUuid(uuidAluno);
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
