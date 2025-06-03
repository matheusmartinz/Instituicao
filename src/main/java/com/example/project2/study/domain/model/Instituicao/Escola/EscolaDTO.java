package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class EscolaDTO {
    @NotNull
    public String nome;
    @NotNull
    public EnderecoDTO endereco;
    public List<PessoaDTO> pessoas = new LinkedList<>();
    public List<SalaDTO> salas = new LinkedList<>();
    public UUID uuid;

    public EscolaDTO(Escola escola) {
        this.nome = escola.getNome().toLowerCase();
        this.endereco = new EnderecoDTO(escola.getEndereco());
        this.pessoas = PessoaDTO.listOfPessoaDTO(escola.getPessoas());
        this.salas = SalaDTO.listOf(escola.getSalas());
        this.uuid = escola.getUuid();
    }
}
