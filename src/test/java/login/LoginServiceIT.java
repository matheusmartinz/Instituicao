package login;

import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.domain.model.Instituicao.Login.Login;
import com.example.project2.study.domain.model.Instituicao.Login.LoginDTO;
import com.example.project2.study.domain.model.Instituicao.Login.LoginService;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


public class LoginServiceIT extends AbstractIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin() {
        LoginDTO loginDTO = new LoginDTO("testeunitario@gmail.com", null, "teste123");
        Login login = loginService.authenticLogin(loginDTO);

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(login.getLogin()).isEqualTo("testeunitario@gmail.com");
            s.assertThat(login.getSenha()).isEqualTo("teste123");
        });
    }
}
