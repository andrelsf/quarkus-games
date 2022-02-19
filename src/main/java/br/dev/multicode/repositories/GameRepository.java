package br.dev.multicode.repositories;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.entities.Game;
import br.dev.multicode.exceptions.GameException;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {

  @CacheResult(cacheName = "games")
  public List<Game> list(final Page page)
  {
    return findAll(Sort.descending("createdAt"))
        .page(page.index, page.size)
        .list();
  }

  @Transactional
  @CacheInvalidate(cacheName = "games")
  public void save(final Game game)
  {
    Optional.ofNullable(game)
        .ifPresentOrElse(this::persistAndFlush,
            () -> { throw new GameException.PersistenceException("Failed to save game."); });
  }

  @CacheResult(cacheName = "games")
  public Game findGameById(@CacheKey final UUID gameId) {
    return find("game_id=:gameId", Parameters.with("gameId", gameId.toString())).firstResultOptional()
        .orElseThrow(() ->
            new GameException.NotFoundException("Game not found by id::".concat(gameId.toString())));
  }

  @Transactional
  @CacheInvalidate(cacheName = "games")
  public void update(@CacheKey final UUID gameId, GameRequest gameRequest)
  {
    this.findGameById(gameId)
        .fillWith(gameRequest);
  }

  @Transactional
  @CacheInvalidate(cacheName = "games")
  public void delete(@CacheKey final UUID gameId)
  {
    this.delete("game_id=:gameId", Parameters.with("gameId", gameId.toString()));
  }

  @Transactional
  @CacheInvalidate(cacheName = "games")
  public void updatePartialContent(@CacheKey final UUID gameId, PatchGameRequest patchGameRequest) {
    this.findGameById(gameId)
        .fillWith(patchGameRequest);
  }
}
