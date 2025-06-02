package com.example.project2.study.domain.model.Instituicao;

public class GenericTO<T> {

    public String uuid;
    public String descricao;


    public GenericTO(String uuid, String descricao) {
        this.uuid = uuid;
        this.descricao = descricao;
    }

}
