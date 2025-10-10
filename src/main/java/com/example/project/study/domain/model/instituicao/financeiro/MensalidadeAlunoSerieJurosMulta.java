package com.example.project.study.domain.model.instituicao.financeiro;

import com.example.project.study.domain.model.instituicao.escola.SerieAno;

import java.util.Map;

public class MensalidadeAlunoSerieJurosMulta {

    private MensalidadeAlunoSerieJurosMulta() {
    }

    public static final Map<SerieAno, MensalidadeJurosMultaDTO> VALORES_MENSALIDADES = Map.of(
            SerieAno.PRIMEIRO_ANO, MensalidadeJurosMultaDTO.of(560, 1, 0.03),
            SerieAno.SEGUNDO_ANO, MensalidadeJurosMultaDTO.of(640, 1.1, 0.08),
            SerieAno.TERCEIRO_ANO, MensalidadeJurosMultaDTO.of(720, 1.2, 0.11),
            SerieAno.QUARTO_ANO, MensalidadeJurosMultaDTO.of(800, 1.3, 0.13),
            SerieAno.QUINTO_ANO, MensalidadeJurosMultaDTO.of(880, 1.4, 0.18),
            SerieAno.SEXTO_ANO, MensalidadeJurosMultaDTO.of(950, 1.5, 0.20),
            SerieAno.SETIMO_ANO, MensalidadeJurosMultaDTO.of(1020, 1.7, 0.30),
            SerieAno.OITAVO_ANO, MensalidadeJurosMultaDTO.of(1100, 1.9, 0.40)
    );

    public static MensalidadeJurosMultaDTO findMensalidadeBySerie(SerieAno serieAno) {
        MensalidadeJurosMultaDTO mensalidadeJurosMultaDTO = VALORES_MENSALIDADES.get(serieAno);
        if (mensalidadeJurosMultaDTO == null) {
            throw new IllegalArgumentException(String.format("Nāo possuímos a %s série cadastrada.", serieAno.getValor()));
        }
        return mensalidadeJurosMultaDTO;
    }
}
