package login;

import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.model.Instituicao.Login.LoginDTO;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;


public class LoginServiceIT extends AbstractIntegrationTest {

        @Test
    public void testLogin() {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.login = "matheus@gmail.com";
            loginDTO.senha = "teste123";

            SoftAssertions.assertSoftly(s -> {
                s.assertThat(loginDTO.getLogin()).isEqualTo("matheus@gmail.com");
                s.assertThat(loginDTO.getSenha()).isEqualTo("teste123");
            });
    }
}
