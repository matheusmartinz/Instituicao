package com.example.project2.study.domain.model.Instituicao.Escola.Endereco;

import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findByCidadeAndCepAndEstado(String cidade, String cep, UF estado);

}
