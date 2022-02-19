package br.dev.multicode.exceptions;

public class LiquibaseException {

  public static class InternalServerErrorException extends BaseException
  {
    private static final long serialVersionUID = 5782552782207787924L;

    public InternalServerErrorException(String message)
    {
      super(message);
    }
  }

}
