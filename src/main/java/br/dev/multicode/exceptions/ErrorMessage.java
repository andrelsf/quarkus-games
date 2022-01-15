package br.dev.multicode.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

  // 404
  GAME_NOT_FOUND(404, "Game not found"),

  // 422
  OOPS(422, "Oops failed to process request");

  private int code;
  private String message;

}
