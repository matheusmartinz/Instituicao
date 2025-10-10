package com.example.project.study.domain.model.instituicao.escola.endereco;

import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findByCidadeAndCepAndEstado(String cidade, String cep, UF estado);

}
