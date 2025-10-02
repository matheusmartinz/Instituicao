package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaHorariaPorDisciplina {
    private static final Map<SerieAno, Map<Disciplina, Integer>> cargaHorariaPorSerie = new HashMap<>();

    private static void adicionarCargaHoraria(SerieAno serie, Map<Disciplina, Integer> disciplinas) {
        cargaHorariaPorSerie.put(serie, new HashMap<>(disciplinas));
    }

    static {
        adicionarCargaHoraria(SerieAno.PRIMEIRO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 10,
                        Disciplina.BIOLOGIA, 12,
                        Disciplina.LITERATURA, 12,
                        Disciplina.INGLES, 10,
                        Disciplina.MATEMATICA, 12,
                        Disciplina.PORTUGUES, 12
        ));

        adicionarCargaHoraria(SerieAno.SEGUNDO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 15,
                        Disciplina.BIOLOGIA, 11,
                        Disciplina.LITERATURA, 9,
                        Disciplina.INGLES, 5,
                        Disciplina.MATEMATICA, 15,
                        Disciplina.PORTUGUES, 10
                ));

        adicionarCargaHoraria(SerieAno.TERCEIRO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 30,
                        Disciplina.BIOLOGIA, 5,
                        Disciplina.LITERATURA, 4,
                        Disciplina.INGLES, 10,
                        Disciplina.MATEMATICA, 20,
                        Disciplina.PORTUGUES, 3
                ));
        adicionarCargaHoraria(SerieAno.QUARTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 9,
                        Disciplina.BIOLOGIA, 5,
                        Disciplina.LITERATURA, 4,
                        Disciplina.INGLES, 10,
                        Disciplina.MATEMATICA, 20,
                        Disciplina.PORTUGUES, 30
                ));

        adicionarCargaHoraria(SerieAno.QUINTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 9,
                        Disciplina.LITERATURA, 8,
                        Disciplina.INGLES, 15,
                        Disciplina.MATEMATICA, 25,
                        Disciplina.PORTUGUES, 40
                ));

        adicionarCargaHoraria(SerieAno.SEXTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 9,
                        Disciplina.BIOLOGIA, 5,
                        Disciplina.LITERATURA, 4,
                        Disciplina.INGLES, 10,
                        Disciplina.MATEMATICA, 20,
                        Disciplina.PORTUGUES, 30
                ));

        adicionarCargaHoraria(SerieAno.SETIMO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 8,
                        Disciplina.LITERATURA, 6,
                        Disciplina.INGLES, 12,
                        Disciplina.MATEMATICA, 25,
                        Disciplina.PORTUGUES, 35
                ));

        adicionarCargaHoraria(SerieAno.OITAVO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 8,
                        Disciplina.LITERATURA, 6,
                        Disciplina.INGLES, 12,
                        Disciplina.MATEMATICA, 25,
                        Disciplina.PORTUGUES, 35
                ));
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
