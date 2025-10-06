package com.example.project2.study.domain.model.Instituicao.Escola;

import com.example.project2.study.domain.model.Instituicao.Escola.Endereco.EnderecoDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class EscolaDTO {
    @NotNull
    private String nome;
    @NotNull
    private EnderecoDTO endereco;
    private List<PessoaDTO> pessoas = new LinkedList<>();
    private List<SalaDTO> salas = new LinkedList<>();
    private UUID uuid;

    public EscolaDTO(Escola escola) {
        this.nome = escola.getNome().toLowerCase();
        this.endereco = EnderecoDTO.of(escola.getEndereco());
        this.pessoas = PessoaDTO.listOfPessoaDTO(escola.getPessoas());
        this.salas = SalaDTO.listOf(escola.getSalas());
        this.uuid = escola.getUuid();
    }
}
