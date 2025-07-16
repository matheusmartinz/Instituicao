package com.example.project2.study.domain.model.Instituicao.Escola.Login;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String nome;
    @Column(unique = true)
    private String login;
    private String senha;
    private UUID uuid;
    private StatusAtivacao statusAtivacao = StatusAtivacao.ATIVO;

    public Login(LoginDTO loginDTO) {
        this.setNome(loginDTO.getNome());
        this.setLogin(loginDTO.getLogin());
        this.setSenha(loginDTO.getSenha());
        this.setUuid(UUID.randomUUID());
    }

    public Login() {
    }
}
