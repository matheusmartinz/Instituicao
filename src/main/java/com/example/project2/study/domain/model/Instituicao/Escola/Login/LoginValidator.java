package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public void validateLogin(LoginDTO loginDTO) {
        if(loginDTO.login == null || loginDTO.login.isBlank()) {
            throw new RuntimeException("Favor informar o login.");
        }
        if(loginDTO.nome == null || loginDTO.nome.isBlank()) {
            throw new RuntimeException("Favor informar o nome.");
        }
        if(loginDTO.senha == null || loginDTO.senha.isBlank()) {
            throw new RuntimeException("Favor informar a senha.");
        }
    }

    public void validateProfile(Login login) {
        if(login == null) {
            throw new RuntimeException("Senha Atual Inválida");
        }
    }
}
