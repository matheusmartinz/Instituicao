package com.example.project.study.domain.model.instituicao.escola.endereco;

import com.example.project.study.domain.model.instituicao.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class EnderecoDTO {
    private UUID uuid;
    @NotNull
    private String cep;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;

    private EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.estado = endereco.getEstado().toString();
        this.cidade = endereco.getCidade();
        this.uuid = endereco.getUuid();
    }

    private EnderecoDTO(String cidade, String estado, String cep) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
    }

    public static EnderecoDTO of(Endereco endereco) {
        return new EnderecoDTO(endereco);
    }

    public static EnderecoDTO of(String cidade, String cep, String estado) {
        return new EnderecoDTO(cidade, estado, cep);
    }
}
