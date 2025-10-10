package com.example.project.study.escola;

import com.example.project.study.AbstractIntegrationIT;
import com.example.project.study.domain.model.instituicao.escola.pessoa.MatriculaGenerator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MatriculaGeneratorIT extends AbstractIntegrationIT {

    @Test
    public void gerarMatricula() {
        String matricula = MatriculaGenerator.gerarMatricula();
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(matricula).isNotNull();
            List<String> split = Arrays.stream(matricula.split("-")).toList();
            s.assertThat(split.get(0)).isEqualTo(String.format("%s",LocalDate.now().getYear()));
            s.assertThat(split.get(1).length()).isEqualTo(5);

        });
    }
}
