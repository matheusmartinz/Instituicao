package com.example.project.study.domain.model.instituicao.login;

public class SuperValidator {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}
