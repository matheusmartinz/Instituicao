package com.example.project2.study.domain.model.Instituicao;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.UF;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
public class Endereco extends EntidadeIdUUID {
    private String cep;
    @Enumerated(EnumType.STRING)
    private UF estado;
    private String cidade;

    private Endereco(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.getCep().replace("-", "");
        this.cidade = enderecoDTO.getCidade();
        this.estado = UF.valueOf(enderecoDTO.getEstado());
    }

    public static Endereco of(EnderecoDTO enderecoDTO) {
      return new Endereco(enderecoDTO);
    }

    public String getDescritivoCidadeUF() {
     return String.format("%s - %s", cidade,estado);
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("implementar metodo");
//        return false;
    }
}
