package com.example.project2.study.domain.model.Instituicao.Escola.Endereco;

import com.example.project2.study.domain.model.Instituicao.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class EnderecoDTO {
    public UUID uuid;
    @NotNull
    public String cep;
    @NotNull
    public String cidade;
    @NotNull
    public String estado;

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.estado = endereco.getEstado().toString();
        this.cidade = endereco.getCidade();
        this.uuid = endereco.getUuid();
    }

    public EnderecoDTO(String cidade, String estado, String cep) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
    }

    public static  EnderecoDTO of(Endereco endereco) {
        EnderecoDTO toReturn = new EnderecoDTO();
        toReturn.cep = endereco.getCep();
        toReturn.estado = endereco.getEstado().toString();
        toReturn.cidade = endereco.getCidade();
        toReturn.uuid = endereco.getUuid();
        return toReturn;
    }

    public static EnderecoDTO of(String cidade, String cep, String estado) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.cidade = cidade;
        enderecoDTO.cep = cep;
        enderecoDTO.estado = estado;
        return enderecoDTO;
    }
}
