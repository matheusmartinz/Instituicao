package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import com.example.project2.study.domain.Repositories.LoginRepository;
import com.example.project2.study.domain.model.Empresa.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends EntidadeService<Login> {

    @Autowired
    private LoginRepository loginRepository;

    public LoginDTO createLogin(LoginDTO loginDTO) {
        Login login = new Login(loginDTO);
        Login save = save(login);
        return LoginDTO.of(save);
    }

    public Login authenticLogin(LoginDTO loginDTO) {
        return loginRepository.findByLoginAndSenha(loginDTO.login, loginDTO.senha);
    }

    @Override
    protected JpaRepository<Login, Long> repository() {
        return loginRepository;
    }
}
