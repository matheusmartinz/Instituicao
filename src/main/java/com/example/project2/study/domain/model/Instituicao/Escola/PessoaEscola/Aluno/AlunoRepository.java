package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByUuid(UUID uuidAluno);
}
