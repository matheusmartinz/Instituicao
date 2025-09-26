package Instituicao.Login;

import com.example.project2.study.AbstractIntegrationTest;
import com.example.project2.study.Exceptions.LoginExceptions;
import com.example.project2.study.domain.Repositories.LoginRepository;
import com.example.project2.study.domain.model.Instituicao.Login.LoginDTO;
import com.example.project2.study.domain.model.Instituicao.Login.LoginService;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.UUID;

public class LoginServiceIT extends AbstractIntegrationTest {

    @Autowired
    LoginService loginService;

    @Autowired
    LoginRepository loginRepository;

    @Test(expectedExceptions = LoginExceptions.class,
            expectedExceptionsMessageRegExp = "Favor informar o login.")
    public void createLoginDefault() {
        LoginDTO loginDTO = new LoginDTO();
        loginService.createLogin(loginDTO);
    }

    @Test(expectedExceptions = LoginExceptions.class,
            expectedExceptionsMessageRegExp = "Favor informar o login.")
    public void createLoginWithoutLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setSenha("<PASSWORD>");
        loginDTO.setNome("Matheus");
        loginDTO.setUuid(UUID.randomUUID());
        loginService.createLogin(loginDTO);
    }

    @Test(expectedExceptions = LoginExceptions.class,
            expectedExceptionsMessageRegExp = "Favor informar o nome.")
    public void createLoginOnlyEmail() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin("matheusdoaiaiphone@gmail.com");
        loginService.createLogin(loginDTO);
    }

    @Test(expectedExceptions =  LoginExceptions.class, expectedExceptionsMessageRegExp = "Email já utilizado.")
    public void createLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin("paipai@gmail.com");
        loginDTO.setSenha("<PASSWORD>");
        loginDTO.setNome("Matheus");
        loginDTO.setUuid(UUID.randomUUID());
        loginService.createLogin(loginDTO);
    }

    @Test(expectedExceptions = LoginExceptions.class, expectedExceptionsMessageRegExp = "Favor informar a senha." )
    public void createLoginWithSenhaBlank(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.login = "<EMAIL>";
        loginDTO.nome = "Matheus";
        loginDTO.senha = "";
        loginService.createLogin(loginDTO);

    }

    @Test(expectedExceptions = LoginExceptions.class, expectedExceptionsMessageRegExp = "Favor informar a senha." )
    public void createLoginWithSenhaNull(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.login = "<EMAIL>";
        loginDTO.nome = "andre";
        loginService.createLogin(loginDTO);

    }

    @Test
    public void createLoginWithAllFields() {
        long before = loginRepository.count();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.login = "<EMAIL>";
        loginDTO.nome = "andre";
        loginDTO.senha = "<PASSWORD>";
        loginDTO.setUuid(UUID.randomUUID());
        loginService.createLogin(loginDTO);
        long after = loginRepository.count();

        SoftAssertions.assertSoftly(s -> {
            s.assertThat(after).isEqualTo(before + 1);
        });
    }

    @Test(expectedExceptions = LoginExceptions.class, expectedExceptionsMessageRegExp = "Formato de email inválido.")
    public void testLogin() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.login = "<EMAIL>";
        loginDTO.senha = "<PASSWORD>";
        loginService.createLogin(loginDTO);
    }
}
