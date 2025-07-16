package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByUuid(UUID uuidAluno);
}
