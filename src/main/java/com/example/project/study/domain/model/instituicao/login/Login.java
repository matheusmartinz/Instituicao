package com.example.project.study.domain.model.instituicao.login;

import com.example.project.study.domain.model.entidadeuuid.EntidadeIdUUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Login extends EntidadeIdUUID {
    private String nome;
    @Column(unique = true)
    private String login;
    private String senha;
    private StatusAtivacao statusAtivacao = StatusAtivacao.ATIVO;
    private UUID uuid;

    private Login(LoginDTO loginDTO) {
        this.setNome(loginDTO.getNome());
        this.setLogin(loginDTO.getLogin());
        this.setSenha(loginDTO.getSenha());
        this.setUuid(UUID.randomUUID());
    }

    public static Login of(LoginDTO loginDTO) {
        return new Login(loginDTO);
    }

    public void updateLogin(LoginDTO loginDTO) {
        this.setNome(loginDTO.getNome());
        this.setLogin(loginDTO.getLogin());
        this.setSenha(loginDTO.getSenha());
    }
}
