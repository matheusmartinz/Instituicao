package com.example.project.study.domain.model.empresa;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JogoIndependente extends Jogo {
    private String nomeEquipe;
    private Boolean isFinanciado;

    protected JogoIndependente() {}

    public JogoIndependente(JogoIndependenteDTO jogoIndependenteDTO, EmpresaDesenvolvedora empresaDesenvolvedora) {
        this.setTitulo(jogoIndependenteDTO.getTitulo());
        this.setGeneros(jogoIndependenteDTO.getGeneros());
        this.setPlataforma(jogoIndependenteDTO.getPlataforma());
        this.setDataLancamento(jogoIndependenteDTO.getDataLancamento());
        this.setPreco(jogoIndependenteDTO.getPreco());
        this.setEmpresaDesenvolvedora(empresaDesenvolvedora);
        this.setNomeEquipe(jogoIndependenteDTO.getNomeEquipe());
        this.setIsFinanciado(jogoIndependenteDTO.getIsFinanciado());
    }
}
