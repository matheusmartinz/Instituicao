package com.example.project.study.domain.model.biblioteca;

import com.example.project.study.domain.model.pessoa.Sexo;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class FuncionarioDTO {
    public String nome;
    public Funcao funcao;
    public String tempoExperiencia;
    public Sexo sexo;
    public UUID uuid;

    public FuncionarioDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.funcao = funcionario.getFuncao();
        this.tempoExperiencia = funcionario.getTempoExperiencia();
        this.sexo = funcionario.getSexo();
        this.uuid = UUID.randomUUID();
    }

    public static List<FuncionarioDTO> listOf(List<Funcionario> funcionarios) {

        return funcionarios.stream().map(FuncionarioDTO::new).toList();
    }
}
