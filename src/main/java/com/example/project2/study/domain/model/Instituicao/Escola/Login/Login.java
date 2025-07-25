package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import com.example.project2.study.domain.model.EntidadeUUID.EntidadeIdUUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Login extends EntidadeIdUUID {
    private String nome;
    @Column(unique = true)
    private String login;
    private String senha;
    private StatusAtivacao statusAtivacao = StatusAtivacao.ATIVO;
//    private TipoUsuario tipoUsuario;


    public Login(LoginDTO loginDTO) {
        this.setNome(loginDTO.getNome());
        this.setLogin(loginDTO.getLogin());
        this.setSenha(loginDTO.getSenha());
//        this.setTipoUsuario(TipoUsuario.valueOf(loginDTO.getTipoUsuario()));
        this.setUuid(UUID.randomUUID());
    }

    public Login() {
    }


    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
