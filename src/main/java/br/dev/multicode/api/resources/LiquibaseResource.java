package br.dev.multicode.api.resources;

import br.dev.multicode.exceptions.LiquibaseException;
import io.quarkus.liquibase.LiquibaseFactory;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSetStatus;


@Path("/liquibase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LiquibaseResource {

  @Inject
  LiquibaseFactory liquibaseFactory;

  @GET
  @Path("/changelogs")
  public Response getChangeLogs()
  {
    try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
      return Response.ok(liquibase.getChangeSetStatuses(new Contexts(), new LabelExpression())
              .stream()
              .map(ChangeSetStatus::getRanChangeSet)
              .collect(Collectors.toList()))
          .build();
    } catch (Exception exception) {
      throw new LiquibaseException.InternalServerErrorException(exception.getMessage());
    }
  }

}
