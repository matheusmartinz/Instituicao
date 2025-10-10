package com.example.project.study.domain.model.instituicao;

public class GenericTO<T> {

    public String uuid;
    public String descricao;


    public GenericTO(String uuid, String descricao) {
        this.uuid = uuid;
        this.descricao = descricao;
    }

}
