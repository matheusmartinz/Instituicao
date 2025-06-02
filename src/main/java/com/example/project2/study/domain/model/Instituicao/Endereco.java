package com.example.project2.study.domain.model.Instituicao;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.UF;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(
        name = "endereco",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cep", "cidade", "estado"})
)
public class Endereco extends EntidadeIdUUID {
    private String cep;
    @Enumerated(EnumType.STRING)
    private UF estado;
    private String cidade;

    public Endereco() {}

    public Endereco(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.cep.replaceAll("-", "");
        this.cidade = enderecoDTO.cidade;
        this.estado = UF.valueOf(enderecoDTO.estado);
    }

    public String getDescritivoCidadeUF() {
     return String.format("%s - %s", cidade,estado);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}
