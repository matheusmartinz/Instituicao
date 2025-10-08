package com.example.project2.study.domain.model.Instituicao.Financeiro;

import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;

import java.math.BigDecimal;
import java.util.Map;

public class MensalidadeAluno {

        public static final Map<SerieAno, BigDecimal> VALORES_MENSALIDADES = Map.of(
                SerieAno.PRIMEIRO_ANO, new BigDecimal("560.00"),
                SerieAno.SEGUNDO_ANO, new BigDecimal("640.00"),
                SerieAno.TERCEIRO_ANO, new BigDecimal("720.00"),
                SerieAno.QUARTO_ANO, new BigDecimal("800.00"),
                SerieAno.QUINTO_ANO, new BigDecimal("880.00"),
                SerieAno.SEXTO_ANO, new BigDecimal("950.00"),
                SerieAno.SETIMO_ANO, new BigDecimal("1020.00"),
                SerieAno.OITAVO_ANO, new BigDecimal("1100.00")
        );

        public static BigDecimal findMensalidadeBySerie(SerieAno serieAno) {
            return VALORES_MENSALIDADES.get(serieAno);
        }
}
