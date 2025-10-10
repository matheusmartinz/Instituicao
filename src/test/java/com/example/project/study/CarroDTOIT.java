package com.example.project.study;

import com.example.project.study.domain.model.carro.Cor;
import com.example.project.study.dtos.carro.CarroDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class CarroDTOIT extends AbstractIntegrationIT {

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
