package br.dev.multicode.exceptions.handlers;

import static br.dev.multicode.exceptions.ErrorMessage.GAME_NOT_FOUND;
import static br.dev.multicode.exceptions.ErrorMessage.OOPS;

import br.dev.multicode.exceptions.GameException;
import io.vertx.core.json.JsonObject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class RestExceptionHandler implements ExceptionMapper<Throwable> {

  private static final Logger log = Logger.getLogger(RestExceptionHandler.class);

  @Override
  public Response toResponse(Throwable ex)
  {
    if (ex instanceof GameException.NotFoundException){
      log.error(ex.getMessage());
      return this.errorResponse(GAME_NOT_FOUND.getCode(), GAME_NOT_FOUND.getMessage());
    }

    log.error(ex.getMessage());
    return this.errorResponse(OOPS.getCode(), OOPS.getMessage());
  }

  private Response errorResponse(int statusCode, String message)
  {
    return Response.status(statusCode)
        .entity(this.apiError(statusCode, message))
        .build();
  }

  private JsonObject apiError(int code, final String message)
  {
    return new JsonObject()
        .put("code", code)
        .put("message", message);
  }

}
