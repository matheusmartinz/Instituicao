package com.example.project2.study.domain.model.Biblioteca;

import com.example.project2.study.domain.model.Biblioteca.Models.Biblioteca;
import com.example.project2.study.domain.model.Pessoa.Sexo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Funcao funcao;
    private String tempoExperiencia;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    public Funcionario(FuncionarioDTO funcionarioDTO) {
        this.setNome(funcionarioDTO.nome);
        this.setFuncao(funcionarioDTO.getFuncao());
        this.setTempoExperiencia(funcionarioDTO.tempoExperiencia);
        this.setSexo(funcionarioDTO.sexo);
        this.setUuid(funcionarioDTO.getUuid());
    }

    protected Funcionario() {}

    public static List<Funcionario> listOf(List<FuncionarioDTO> funcionarioDTOS) {
        return funcionarioDTOS.stream().map(Funcionario::new).toList();
    }
}
