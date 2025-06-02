package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.PessoaEmEscola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaTelefoneRepository extends JpaRepository<PessoaTelefone, Long> {
}
