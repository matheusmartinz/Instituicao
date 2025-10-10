package com.example.project.study.domain.model.instituicao.escola.endereco;

import com.example.project.study.domain.model.empresa.EntidadeService;
import com.example.project.study.domain.model.instituicao.Endereco;
import com.example.project.study.domain.model.instituicao.escola.UF;
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
