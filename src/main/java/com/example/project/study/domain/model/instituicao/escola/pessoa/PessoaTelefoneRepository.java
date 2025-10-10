package com.example.project.study.domain.model.instituicao.escola.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaTelefoneRepository extends JpaRepository<PessoaTelefone, Long> {
}
