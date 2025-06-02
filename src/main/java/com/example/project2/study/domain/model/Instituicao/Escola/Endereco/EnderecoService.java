package com.example.project2.study.domain.model.Instituicao.Escola.Endereco;

import com.example.project2.study.domain.model.Empresa.EntidadeService;
import com.example.project2.study.domain.model.Instituicao.Endereco;
import com.example.project2.study.domain.model.Instituicao.Escola.UF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends EntidadeService<Endereco> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public JpaRepository<Endereco, Long> repository() {
        return enderecoRepository;
    }

    public Endereco saveEndereco(Endereco endereco) {
        return super.save(endereco);
    }

    public Endereco findByCidadeAndCepAndEstado(String cidade, String cep, UF estado) {
        return enderecoRepository.findByCidadeAndCepAndEstado(cidade, cep, estado);
    }
}
