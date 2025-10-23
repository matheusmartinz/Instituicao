package com.example.project.study.domain.model.instituicao.financeiro;

public enum StatusPagamento {
    EM_ABERTO,
    PAGO,
    ATRASADO,
    ISENTO,
    EM_ANALISE,
    CONCLUIDO;

    public boolean isIsento() {
        return this == ISENTO;
    }

    public boolean isPago() {
        return this == PAGO || this == ISENTO;
    }

    public boolean isAtrasado() {
        return this == ATRASADO;
    }

    public boolean isAberto() {
        return this == EM_ABERTO;
    }
}
