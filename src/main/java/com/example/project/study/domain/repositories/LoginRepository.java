package com.example.project.study.domain.repositories;

import com.example.project.study.domain.model.instituicao.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByLogin(String login);
    Login findByUuid(UUID uuid);
    Login findByLoginAndSenha(String login, String senha);
    Login findByUuidAndSenha(UUID uuid, String senha);
}
