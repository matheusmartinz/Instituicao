package com.example.project2.study.domain.model.Instituicao.Escola.Login;

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
