package br.dev.multicode.exceptions;

public class GameException {

  public static class NotFoundException extends BaseException
  {
    private static final long serialVersionUID = -6673595023168616262L;

    public NotFoundException(String message)
    {
      super(message);
    }
  }

  public static  class PersistenceException extends BaseException
  {
    private static final long serialVersionUID = 581116316483482160L;

    public PersistenceException(String message) { super(message); }
  }

}
