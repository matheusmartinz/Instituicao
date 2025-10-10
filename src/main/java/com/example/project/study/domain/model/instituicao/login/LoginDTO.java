package com.example.project.study.domain.model.instituicao.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class LoginDTO {
    private UUID uuid;
    private String nome;
    private String login;
    private String senha;
//    public String tipoUsuario;

    private LoginDTO(Login login) {
        this.nome = login.getNome();
        this.login = login.getLogin();
        this.senha = login.getSenha();
        this.uuid = login.getUuid();
    }

    public LoginDTO(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public static LoginDTO of(Login login) {
        return new LoginDTO(login);
    }

}
