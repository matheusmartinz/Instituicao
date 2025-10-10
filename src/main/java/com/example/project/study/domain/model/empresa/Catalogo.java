package com.example.project.study.domain.model.empresa;

import java.util.LinkedList;
import java.util.List;

public class Catalogo {
    List<Jogo> jogosDisponiveis = new LinkedList<>();

    public void adicionarJogo(Jogo jogo) {
        this.jogosDisponiveis.add(jogo);
    }

    public void removerJogo(Jogo jogo) {
        this.jogosDisponiveis.remove(jogo);
    }

}
