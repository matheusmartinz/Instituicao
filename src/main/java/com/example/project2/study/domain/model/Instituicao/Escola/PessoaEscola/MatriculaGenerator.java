package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola;

import java.time.Year;
import java.util.Random;

public class MatriculaGenerator {

    public static String gerarMatricula() {
        int ano = Year.now().getValue();
        int numeroAleatorio = new Random().nextInt(100000);
        return String.format("%d-%05d", ano, numeroAleatorio);
    }
}
