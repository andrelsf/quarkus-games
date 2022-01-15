package br.dev.multicode.exceptions;

public abstract class BaseException extends RuntimeException {

  private String message;

  public BaseException(String message) { this.message = message; }

  public String getMessage() { return this.message; }

}
