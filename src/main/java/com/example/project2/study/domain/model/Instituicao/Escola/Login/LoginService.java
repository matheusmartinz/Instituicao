package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import com.example.project2.study.domain.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginDTO createLogin(LoginDTO loginDTO) {
        Login login = new Login(loginDTO);
        Login save = loginRepository.save(login);
        return LoginDTO.of(save);
    }

    public Login authenticLogin(LoginDTO loginDTO) {
        return  loginRepository.findByLoginAndSenha(loginDTO.login, loginDTO.senha);
    }
}
