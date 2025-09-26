package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Instituicao.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByLogin(String login);
    Login findByUuid(UUID uuid);
    Login findByLoginAndSenha(String login, String senha);
    Login findByUuidAndSenha(UUID uuid, String senha);
}
