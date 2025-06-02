package com.example.project2.study.domain.model.Empresa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class EmpresaDesenvolvedora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private String nome;
    private String paisOrigem;
    private Integer anoFundacao;

    public EmpresaDesenvolvedora(EmpresaDesenvolvedoraDTO empresaDesenvolvedoraDTO) {
        this.setNome(empresaDesenvolvedoraDTO.getNome());
        this.setPaisOrigem(empresaDesenvolvedoraDTO.getPaisOrigem());
        this.setAnoFundacao(empresaDesenvolvedoraDTO.getAnoFundacao());
    }

    private EmpresaDesenvolvedora() {}

    public EmpresaDesenvolvedora(String nome) {
        this.setNome(nome);
    }

    public static EmpresaDesenvolvedora of() {
        return new EmpresaDesenvolvedora();
    }

    public static EmpresaDesenvolvedora of(String nome) {
        return new EmpresaDesenvolvedora(nome);
    }
}
