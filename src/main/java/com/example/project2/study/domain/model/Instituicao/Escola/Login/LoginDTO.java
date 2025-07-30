package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class LoginDTO {
    public UUID uuid;
    public String nome;
    public String login;
    public String senha;
//    public String tipoUsuario;

    public static LoginDTO of(Login login) {
        LoginDTO toReturn = new LoginDTO();
        toReturn.nome = login.getNome();
        toReturn.login = login.getLogin();
        toReturn.uuid = login.getUuid();
//        toReturn.tipoUsuario = login.getTipoUsuario().toString();
        return toReturn;
    }

}
