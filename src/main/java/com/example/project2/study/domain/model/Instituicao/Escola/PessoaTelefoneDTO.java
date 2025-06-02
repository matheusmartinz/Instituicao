package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.EscolaPessoa.PessoaTelefone;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
@Getter
public class PessoaTelefoneDTO {
    public String ddd;
    public String fone;
    public UUID pessoaUUID;

    public PessoaTelefoneDTO(PessoaTelefone telefone) {
        this.ddd = ofNullable(telefone).map(PessoaTelefone::getDdd).orElse(null);
        this.fone = ofNullable(telefone).map(PessoaTelefone::getFone).orElse(null);
        this.pessoaUUID = ofNullable(telefone).map(PessoaTelefone::getPessoaUUID).orElse(null);
    }

    public String getDescritivo() {
        if (ddd == null || fone == null || fone.length() < 9) {
            return null;
        }

        return String.format("(%s) %s %s-%s",
                ddd,
                fone.substring(0, 1),
                fone.substring(1, 6),
                fone.substring(6));
    }

}
