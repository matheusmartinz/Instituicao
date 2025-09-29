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

    @Autowired
    SalaRepository salaRepository;

//    @Test
//    public void updateSala() {
//
//        SalaDTO salaDTO = new SalaDTO();
//        salaDTO.numeroSala = "500";
//        salaDTO.serieAno = SerieAno.TERCEIRO_ANO;
//        long before = salaRepository.count();
//        salaService.updateSalaByUuid(salaDTO, UUID.fromString("ea0ce285-7f59-457b-8fbc-84d12ba636ac"));
//        long after = salaRepository.count();
//
//        SoftAssertions.assertSoftly(s -> {
//            s.assertThat(salaDTO.numeroSala).isEqualTo("500");
//            s.assertThat(salaDTO.serieAno).isEqualTo(SerieAno.TERCEIRO_ANO);
//            s.assertThat(after).isEqualTo(before);
//        });
//    }

}
