//import com.example.project2.study.StudyApplication;
//import com.example.project2.study.domain.Repositories.PessoaRepository;
//import com.example.project2.study.domain.model.Carro.Cor;
//import com.example.project2.study.domain.model.Pessoa.Pessoa;
//import com.example.project2.study.dtos.Pessoa.PessoaDTO;
//import org.assertj.core.api.SoftAssertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
//@SpringBootTest(classes = StudyApplication.class)
//@ActiveProfiles("test")
//@Transactional
//public class CorTest {
//
//    @Autowired
//    private PessoaRepository pessoaRepository;
//
////    @Test
////    public void testCor() {
////        Cor verde = Cor.fromString("verde");
////        SoftAssertions.assertSoftly(s -> {
////            s.assertThat(verde.equals(Cor.VERMELHO));
////        });
////    }
//
//    @Test
//    public void testCor() {
//        Cor verde = Cor.fromString("verde");
//        Cor vermelho = Cor.fromString("Vermelho");
//        Cor vermelhoTwo = Cor.fromString("VeRmElHo");
//        Cor azul = Cor.fromString("azul");
//        Cor nula = Cor.fromString(null);
//
//        SoftAssertions.assertSoftly(s-> {
//            s.assertThat(verde).isEqualTo(Cor.VERDE);
//            s.assertThat(vermelho).isEqualTo(Cor.VERMELHO);
//            s.assertThat(vermelhoTwo).isEqualTo(Cor.VERMELHO);
//            s.assertThat(azul).isEqualTo(Cor.AZUL);
//            s.assertThat(nula).isNull();
//        });
//    }
//
////    @Test
////    public void testPessoa() {
////        Pessoa byUuid = pessoaRepository.findByUuid(UUID.fromString("2422c232-5506-4b64-91fa-fede4a3e366c"));
////        PessoaDTO dto = PessoaDTO(byUuid);
////        SoftAssertions.assertSoftly(s -> {
////            s.assertThat(dto.getNome()).isEqualTo("Matheus");
////            s.assertThat(dto.getSobrenome()).isEqualTo("FRANCO");
////        });
////    }
//
//    @Test
//    public void testeNewPessoa() {
//        long beforeCount = pessoaRepository.count();
//
//        PessoaDTO pessoaDTO = new PessoaDTO();
//        pessoaDTO.setNome("Andre");
//        pessoaDTO.setSobrenome("Franco");
//        pessoaDTO.setCpf("11085139999");
//
//        Pessoa pessoa = Pessoa(pessoaDTO);
//        pessoaRepository.save(pessoa);
//
//        long afterCount = pessoaRepository.count();
//
//        SoftAssertions.assertSoftly(s -> {
//            s.assertThat(afterCount).isEqualTo(beforeCount + 1);
//        });
//    }
//}
