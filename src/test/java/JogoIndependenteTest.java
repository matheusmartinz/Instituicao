//import com.example.project.study.dataproviders.JogoDTODataProvider;
//import com.example.project.study.StudyApplication;
//import com.example.project.study.domain.repositories.EmpresaDesenvolvedoraRepository;
//import com.example.project.study.domain.repositories.JogoIndependenteRepository;
//import com.example.project.study.domain.model.empresa.EmpresaDesenvolvedora;
//import com.example.project.study.domain.model.empresa.JogoIndependenteDTO;
//import com.example.project.study.domain.model.empresa.JogoIndependente;
//import org.assertj.core.api.SoftAssertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.UUID;
//
//@SpringBootTest(classes = StudyApplication.class)
//@ActiveProfiles("test")
//public class JogoIndependenteTest {
//
//    @Autowired
//    private JogoIndependenteRepository jogoIndependenteRepository;
//
//    @Autowired
//    private EmpresaDesenvolvedoraRepository empresaDesenvolvedoraRepository;
//
//    @Test
//    public void test() {
//        EmpresaDesenvolvedora empresaDesenvolvedora =  EmpresaDesenvolvedora.of();
//        empresaDesenvolvedora.setUuid(UUID.randomUUID());
//        empresaDesenvolvedora.setNome("QUALQUER COISA");
//        empresaDesenvolvedora.setAnoFundacao(1975);
//        empresaDesenvolvedora.setPaisOrigem("JAPAO");
//        empresaDesenvolvedoraRepository.save(empresaDesenvolvedora);
//
//        long before = jogoIndependenteRepository.count();
//        JogoIndependenteDTO darkSouls = JogoDTODataProvider.getDarkSouls(empresaDesenvolvedora.getUuid());
//        EmpresaDesenvolvedora load = empresaDesenvolvedoraRepository.findByUuid(darkSouls.getUuidEmpresaDesenvolvedora());
//        if (load  == null) {
//            empresaDesenvolvedoraRepository.save(load);
//        }
//        JogoIndependente jogoIndependente = new JogoIndependente(darkSouls, load);
//
//
//        jogoIndependente.setUuid(UUID.randomUUID());
//        jogoIndependenteRepository.save(jogoIndependente);
//
//        long after = jogoIndependenteRepository.count();
//
//        SoftAssertions.assertSoftly(s -> {
//            s.assertThat(before).isEqualTo(1);
//            s.assertThat(after).isEqualTo(before + 1);
//        });
//    }
//}
