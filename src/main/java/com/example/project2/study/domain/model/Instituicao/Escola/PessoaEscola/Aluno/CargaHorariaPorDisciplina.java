package com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.Aluno;

import com.example.project2.study.domain.model.Instituicao.Disciplina;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CargaHorariaPorDisciplina {
    private static final Map<SerieAno, Map<Disciplina, Integer>> cargaHorariaPorSerie = new EnumMap<>(SerieAno.class);

    private CargaHorariaPorDisciplina() {
    }

    private static void adicionarCargaHoraria(SerieAno serie, Map<Disciplina, Integer> disciplinas) {
        cargaHorariaPorSerie.put(serie, new EnumMap<>(disciplinas));
    }

    static {
        adicionarCargaHoraria(SerieAno.PRIMEIRO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 11,
                        Disciplina.LITERATURA, 10,
                        Disciplina.INGLES, 11,
                        Disciplina.MATEMATICA, 10,
                        Disciplina.PORTUGUES, 11
                ));

        adicionarCargaHoraria(SerieAno.SEGUNDO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 11,
                        Disciplina.BIOLOGIA, 12,
                        Disciplina.LITERATURA, 10,
                        Disciplina.INGLES, 10,
                        Disciplina.MATEMATICA, 11,
                        Disciplina.PORTUGUES, 11
                ));

        adicionarCargaHoraria(SerieAno.TERCEIRO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 13,
                        Disciplina.BIOLOGIA, 10,
                        Disciplina.LITERATURA, 9,
                        Disciplina.INGLES, 12,
                        Disciplina.MATEMATICA, 10,
                        Disciplina.PORTUGUES, 10
                ));

        adicionarCargaHoraria(SerieAno.QUARTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 7,
                        Disciplina.BIOLOGIA, 10,
                        Disciplina.LITERATURA, 12,
                        Disciplina.INGLES, 13,
                        Disciplina.MATEMATICA, 10,
                        Disciplina.PORTUGUES, 10
                ));

        adicionarCargaHoraria(SerieAno.QUINTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 10,
                        Disciplina.BIOLOGIA, 11,
                        Disciplina.LITERATURA, 11,
                        Disciplina.INGLES, 11,
                        Disciplina.MATEMATICA, 12,
                        Disciplina.PORTUGUES, 11
                ));

        adicionarCargaHoraria(SerieAno.SEXTO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 11,
                        Disciplina.BIOLOGIA, 10,
                        Disciplina.LITERATURA, 10,
                        Disciplina.INGLES, 11,
                        Disciplina.MATEMATICA, 12,
                        Disciplina.PORTUGUES, 10
                ));

        adicionarCargaHoraria(SerieAno.SETIMO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 11,
                        Disciplina.LITERATURA, 10,
                        Disciplina.INGLES, 12,
                        Disciplina.MATEMATICA, 11,
                        Disciplina.PORTUGUES, 11
                ));

        adicionarCargaHoraria(SerieAno.OITAVO_ANO,
                Map.of(
                        Disciplina.GEOGRAFIA, 12,
                        Disciplina.BIOLOGIA, 11,
                        Disciplina.LITERATURA, 10,
                        Disciplina.INGLES, 12,
                        Disciplina.MATEMATICA, 11,
                        Disciplina.PORTUGUES, 11
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
