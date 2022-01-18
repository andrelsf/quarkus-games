package br.dev.multicode.exceptions.handlers;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class BeanValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

  private static final Logger log = Logger.getLogger(BeanValidationExceptionHandler.class);

  @Override
  public Response toResponse(ConstraintViolationException exception) {
    return Response.status(Status.BAD_REQUEST)
        .entity(createErrorMessage(exception))
        .build();
  }

  private JsonArray createErrorMessage(ConstraintViolationException ex) {
    JsonArrayBuilder errors = Json.createArrayBuilder();

    for (ConstraintViolation<?> violation : ex.getConstraintViolations())
    {
      final String propertyPath = violation.getPropertyPath().toString();
      final String message = violation.getMessage();

      errors.add(Json.createObjectBuilder()
          .add("path", propertyPath)
          .add("message", message));

      log.error("path: ".concat(propertyPath)
          .concat(" message: ".concat(message)));
    }

    return errors.build();
  }
}
