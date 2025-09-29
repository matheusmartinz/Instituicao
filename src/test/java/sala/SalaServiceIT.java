package sala;

import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.StudyApplication;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.Escola;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.Sala;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaDTO;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaService;
import com.example.project2.study.domain.model.Instituicao.Escola.SerieAno;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

import java.util.UUID;

@SpringBootTest(classes = StudyApplication.class)
@ActiveProfiles("test")
public class SalaServiceIT extends AbstractIntegrationTest {

    @Autowired
    private SalaService salaService;

    @Autowired
    private EscolaRepository escolaRepository;

    @Test
    public void updateSala() {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.numeroSala = "500";
        salaDTO.serieAno = SerieAno.QUARTO_ANO;
//        long before = salaRepository.count();
        salaService.updateSalaByUuid(salaDTO, UUID.fromString("a823bd7e-33ab-4342-940c-1cbb7b9a0116"));
//        long after = salaRepository.count();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(salaDTO.numeroSala).isEqualTo("500");
            s.assertThat(salaDTO.serieAno.getValor()).isEqualTo(SerieAno.QUARTO_ANO.getValor());

//            s.assertThat(after).isEqualTo(before+1);
        });
    }

}
