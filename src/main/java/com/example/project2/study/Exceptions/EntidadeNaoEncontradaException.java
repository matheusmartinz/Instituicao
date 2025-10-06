package com.example.project2.study.Exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

  public EntidadeNaoEncontradaException(String message) {
        super(message);
  }

  public EntidadeNaoEncontradaException() {
    super("Entidade não encontrada");
  }

}
