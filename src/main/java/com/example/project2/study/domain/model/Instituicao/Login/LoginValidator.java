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

        if (isNull(loginDTO.getLogin()) || isBlank(loginDTO.getLogin())) {
            throw new LoginExceptions("Favor informar o login.");
        }
        if(loginDTO.getLogin().matches(isValidEmail) == false){
            throw new LoginExceptions("Formato de email inválido.");
        }
        if (isNull(loginDTO.getNome()) || isBlank(loginDTO.getNome())) {
            throw new LoginExceptions("Favor informar o nome.");
        }
        if (isNull(loginDTO.getSenha()) || isBlank(loginDTO.getSenha())) {
            throw new LoginExceptions("Favor informar a senha.");
        }
    }

    public void cadastroLoginDTO(LoginDTO loginDTO) {
        Login login = loginRepository.findByLogin(loginDTO.getLogin());
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
