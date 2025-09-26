package com.example.project2.study.domain.model.Instituicao.Login;

public class SuperValidator {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}
