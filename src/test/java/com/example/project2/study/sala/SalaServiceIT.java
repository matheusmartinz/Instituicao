package com.example.project2.study.sala;

import com.example.project2.study.AbstractIntegrationIT;
import com.example.project2.study.StudyApplication;
import com.example.project2.study.domain.Repositories.EscolaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaRepository;
import com.example.project2.study.domain.model.Instituicao.Escola.EscolaSala.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = StudyApplication.class)
@ActiveProfiles("test")
public class SalaServiceIT extends AbstractIntegrationIT {

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
