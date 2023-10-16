package com.betrybe.agrix.exception;

public class FarmNotFoundException extends RuntimeException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
