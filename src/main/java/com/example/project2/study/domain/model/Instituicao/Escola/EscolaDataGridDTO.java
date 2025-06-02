package com.example.project2.study.domain.model.Instituicao.Escola;

import java.util.UUID;

public class EscolaDataGridDTO {
    public String nome;
    public String cidade;
    public String cep;
    public String estado;
    public Integer pessoas;
    public Integer salas;
    public UUID uuid;

    public EscolaDataGridDTO(Escola escola) {
        this.nome = escola.getNome().toUpperCase();
        this.cidade = escola.getEndereco().getCidade();
        this.cep = escola.getEndereco().getCep();
        this.estado = escola.getEndereco().getEstado().toString();
        this.pessoas = escola.getPessoas().size();
        this.salas = escola.getSalas().size();
        this.uuid = escola.getUuid();
    }
}
