package com.example.project2.study.domain.Repositories;

import com.example.project2.study.domain.model.Instituicao.Escola.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByLogin(String login);

    Login findByLoginAndSenha(String login, String senha);
    Login findByUuidAndSenha(UUID uuid, String senha);
}
