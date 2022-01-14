package br.dev.multicode.api.resources;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.services.GameService;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/api/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

  @Inject
  GameService gameService;

  @POST
  public Response postGame(@Valid GameRequest postGameRequest)
  {
    gameService.create(postGameRequest);
    return Response.status(Status.CREATED).build();
  }

  @GET
  @Path("/{gameId}")
  public Response getGameById(@PathParam("gameId") UUID gameId)
  {
    return Response.status(Status.OK)
        .entity(gameService.findById(gameId))
        .build();
  }

  @PUT
  @Path("/{gameId}")
  public Response putGame(@PathParam("gameId") UUID gameId, @Valid GameRequest putGameRequest)
  {
    gameService.update(gameId, putGameRequest);
    return Response.status(Status.NO_CONTENT).build();
  }

  @DELETE
  @Path("/{gameId}")
  public Response deleteGame(@PathParam("gameId") UUID gameId)
  {
    gameService.delete(gameId);
    return Response.status(Status.NO_CONTENT).build();
  }

  @PATCH
  @Path("/{gameId}")
  public Response patchGame(@PathParam("gameId") UUID gameId, PatchGameRequest patchGameRequest)
  {
    gameService.updatePartialContent(gameId, patchGameRequest);
    return Response.status(Status.NO_CONTENT).build();
  }
}
