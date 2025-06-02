package com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.PessoaTelefoneDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@Getter
@Setter
@Entity
public class PessoaTelefone extends EntidadeIdUUID {
    @Column(length = 2)
    private String ddd;
    @Column(length = 9)
    private String fone;
    private UUID pessoaUUID;

    protected PessoaTelefone() {}

    public PessoaTelefone(PessoaTelefoneDTO pessoaTelefoneDTO) {
        this.setDdd(ofNullable(pessoaTelefoneDTO).map(PessoaTelefoneDTO::getDdd).orElse(null));
        this.setFone(ofNullable(pessoaTelefoneDTO).map(PessoaTelefoneDTO::getFone).orElse(null));
        this.setUuid(ofNullable(pessoaTelefoneDTO).map(PessoaTelefoneDTO::getPessoaUUID).orElse(null));
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}

