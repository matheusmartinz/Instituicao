package com.example.project2.study.domain.model.Instituicao.Login;

import com.example.project2.study.Exceptions.LoginExceptions;
import com.example.project2.study.domain.Repositories.LoginRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator extends SuperValidator {

    private final LoginRepository loginRepository;

    public LoginValidator(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void validateLoginDTO(LoginDTO loginDTO) {
        String isValidEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (isNull(loginDTO.login) || isBlank(loginDTO.login)) {
            throw new LoginExceptions("Favor informar o login.");
        }
        if(loginDTO.login.matches(isValidEmail) == false){
            throw new LoginExceptions("Formato de email inválido.");
        }
        if (isNull(loginDTO.nome) || isBlank(loginDTO.nome)) {
            throw new LoginExceptions("Favor informar o nome.");
        }
        if (isNull(loginDTO.senha) || isBlank(loginDTO.senha)) {
            throw new LoginExceptions("Favor informar a senha.");
        }
    }

    public void cadastroLoginDTO(LoginDTO loginDTO) {
        Login login = loginRepository.findByLogin(loginDTO.login);
        if (login != null) {
            throw new LoginExceptions("Email já utilizado.");
        }
    }

    public void validateProfile(Login login) {
        if (login == null) {
            throw new LoginExceptions("Senha Atual Inválida");
        }
    }
}
