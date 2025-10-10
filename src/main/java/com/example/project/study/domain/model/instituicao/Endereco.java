package com.example.project.study.domain.model.instituicao;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import com.example.project.study.domain.model.instituicao.escola.UF;
import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
        return String.format("%s - %s", cidade, estado);
    }
}
