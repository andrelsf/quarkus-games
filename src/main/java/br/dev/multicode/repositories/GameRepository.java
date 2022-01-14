package br.dev.multicode.repositories;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.entities.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import java.util.Optional;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {

  @Transactional
  public void save(final Game game)
  {
    Optional.ofNullable(game)
        .ifPresentOrElse(this::persistAndFlush,
            () -> { throw new PersistenceException("Failed to save game."); });
  }

  public Game findGameById(UUID gameId) {
    return find("game_id=:gameId", Parameters.with("gameId", gameId.toString())).firstResultOptional()
        .orElseThrow(() ->
            new NotFoundException("Game not found by id::".concat(gameId.toString())));
  }

  @Transactional
  public void update(UUID gameId, GameRequest gameRequest)
  {
    this.findGameById(gameId)
        .fillWith(gameRequest);
  }

  @Transactional
  public void delete(UUID gameId)
  {
    final Game game = this.findGameById(gameId);
    this.delete(game);
  }

  @Transactional
  public void updatePartialContent(UUID gameId, PatchGameRequest patchGameRequest) {
    this.findGameById(gameId).fillWith(patchGameRequest);
  }
}
