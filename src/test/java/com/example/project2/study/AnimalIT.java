package com.example.project2.study;

import com.example.project2.study.domain.AnimalBase;
import com.example.project2.study.domain.Cachorro;
import com.example.project2.study.domain.Passaro;
import com.example.project2.study.domain.Porte;
import com.example.project2.study.domain.model.Carro.Cor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class AnimalIT extends AbstractIntegrationIT {

    @Test
    public void cachorro() {
        Cachorro cachorro = new Cachorro();
        cachorro.name = "ANDRE";
        cachorro.porte = Porte.MEDIO;
        cachorro.cor = Cor.PRETO;

        Cachorro rex = new Cachorro();
        rex.name = "MATHEUS";
        rex.porte = Porte.GRANDE;
        rex.cor = Cor.VERDE;

        Passaro passaro = new Passaro();
        new AnimalBase();


        SoftAssertions.assertSoftly(s -> {
            s.assertThat(cachorro.name).isEqualTo("ANDRE");
            s.assertThat(rex.name).isEqualTo("MATHEUS");
            s.assertThat(cachorro.makeSound()).isNotEqualTo(passaro.makeSound());
            s.assertThat(rex.eat()).isNotEqualTo(passaro.eat());
            s.assertThat(rex.move()).isNotEqualTo(passaro.move());
            s.assertThat(rex.sleep()).isEqualTo(passaro.sleep() / 2);
        });

    }


}
