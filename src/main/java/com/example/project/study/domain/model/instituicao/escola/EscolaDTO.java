package com.example.project.study.domain.model.instituicao.escola;

import com.example.project.study.domain.model.instituicao.escola.endereco.EnderecoDTO;
import com.example.project.study.domain.model.instituicao.escola.sala.SalaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
