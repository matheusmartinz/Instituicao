package escola;

import com.example.project2.study.domain.model.Instituicao.Escola.PessoaEscola.MatriculaGenerator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MatriculaGeneratorTest {

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
