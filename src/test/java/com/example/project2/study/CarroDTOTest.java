package com.example.project2.study;

import com.example.project2.study.domain.model.Carro.Cor;
import com.example.project2.study.dtos.Carro.CarroDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CarroDTOTest {

    @Test
    public void test() {
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.nome = "ANDRE";
        carroDTO.cor = (Cor.fromString("VERMELHO").toString());
        String descritivo = carroDTO.getDescritivo();

        CarroDTO carroDTODois = new CarroDTO();
        carroDTODois.nome = ("ANDRE");
        carroDTODois.cor = (Optional.ofNullable(Cor.fromString(null)).map(Enum::toString).orElse(null));
        String descritivoDois = carroDTODois.getDescritivo();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(descritivo).isEqualTo("ANDRE - VERMELHO");
            s.assertThat(descritivoDois).isEqualTo("ANDRE");
        });

    }

}
