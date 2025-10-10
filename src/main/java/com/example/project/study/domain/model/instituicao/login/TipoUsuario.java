package com.example.project.study.domain.model.instituicao.login;

public enum TipoUsuario {
    ALUNO("ALUNO"),
    DIRETOR("DIRETOR"),
    COORDENADOR("COORDENADOR"),
    PROFESSOR("PROFESSOR");

    private String usuario;

    TipoUsuario(String usuario) {
        this.usuario = usuario;
    }
}
