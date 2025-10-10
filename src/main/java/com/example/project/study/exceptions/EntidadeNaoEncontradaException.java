package com.example.project.study.exceptions;

import java.io.Serial;

public class EntidadeNaoEncontradaException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6386085638666238763L;

  public EntidadeNaoEncontradaException(String message) {
        super(message);
  }

  public EntidadeNaoEncontradaException() {
    super("Entidade não encontrada");
  }

}
