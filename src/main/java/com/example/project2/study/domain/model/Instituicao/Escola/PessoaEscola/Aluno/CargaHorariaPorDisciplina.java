package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaHorariaPorDisciplina {
    private static final Map<SerieAno, Map<Disciplina, Integer>> cargaHorariaPorSerie = new HashMap<>();


    static {
        // PRIMEIRO ANO
        Map<Disciplina, Integer> primeiroAno = new HashMap<>();
        primeiroAno.put(Disciplina.MATEMATICA, 100);
        primeiroAno.put(Disciplina.PORTUGUES, 120);
        primeiroAno.put(Disciplina.GEOGRAFIA, 80);
        cargaHorariaPorSerie.put(SerieAno.PRIMEIRO_ANO, primeiroAno);

        // SEGUNDO ANO
        Map<Disciplina, Integer> segundoAno = new HashMap<>();
        segundoAno.put(Disciplina.MATEMATICA, 110);
        segundoAno.put(Disciplina.PORTUGUES, 130);
        segundoAno.put(Disciplina.INGLES, 60);
        cargaHorariaPorSerie.put(SerieAno.SEGUNDO_ANO, segundoAno);


        Map<Disciplina, Integer> quartoAno = new HashMap<>();
        quartoAno.put(Disciplina.MATEMATICA, 110);
        quartoAno.put(Disciplina.PORTUGUES, 130);
        quartoAno.put(Disciplina.INGLES, 60);
        cargaHorariaPorSerie.put(SerieAno.QUARTO_ANO, quartoAno);

    }

    public static int getCargaHoraria(List<Disciplina> disciplinas, String serieAno) {
        Map<Disciplina, Integer> mapa = cargaHorariaPorSerie.getOrDefault(SerieAno.from(serieAno), Map.of());
        Integer cargaHoraria = 0;
        for (Disciplina disciplina : disciplinas) {
            if (mapa.get(disciplina) == null) {
                throw new RuntimeException(String.format("Não tem definido a carga para a disciplina %s para o %s", disciplina, serieAno));
            }
            cargaHoraria += mapa.get(disciplina);
        }

     return cargaHoraria;
    }


}
