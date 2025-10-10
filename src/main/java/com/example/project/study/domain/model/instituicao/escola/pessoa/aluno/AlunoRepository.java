package com.example.project.study.domain.model.instituicao.escola.pessoa.aluno;

import com.example.project.study.domain.model.instituicao.escola.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByUuid(UUID uuidAluno);
}
