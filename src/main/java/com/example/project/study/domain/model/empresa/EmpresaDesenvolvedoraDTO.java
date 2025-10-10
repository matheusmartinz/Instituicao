package com.example.project.study.domain.model.empresa;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmpresaDesenvolvedoraDTO {
        @NotNull
        private String nome;
        @NotNull
        private String paisOrigem;
        @NotNull
        private Integer anoFundacao;

        private UUID uuid;

    public EmpresaDesenvolvedoraDTO(EmpresaDesenvolvedora empresaDesenvolvedora) {
            this.nome = empresaDesenvolvedora.getNome();
            this.paisOrigem = empresaDesenvolvedora.getPaisOrigem();
            this.anoFundacao = empresaDesenvolvedora.getAnoFundacao();
            this.uuid = empresaDesenvolvedora.getUuid();
        }
}
