package br.dev.multicode.services.impl;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.api.http.responses.GameResponse;
import br.dev.multicode.api.interceptor.LogEvent;
import br.dev.multicode.entities.Game;
import br.dev.multicode.repositories.GameRepository;
import br.dev.multicode.services.GameService;
import io.quarkus.panache.common.Page;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GameServiceImpl implements GameService {

  @Inject
  GameRepository gameRepository;

  @Override
  public List<GameResponse> findAll(Page page) {
    return gameRepository.list(page)
        .stream()
        .map(GameResponse::of)
        .collect(Collectors.toList());
  }

  @Override
  @LogEvent
  public void create(GameRequest postGameRequest)
  {
    gameRepository.save(Game.of(postGameRequest));
  }

  @Override
  public void update(UUID gameId, GameRequest putGameRequest)
  {
    gameRepository.update(gameId, putGameRequest);
  }

  @Override
  public void updatePartialContent(UUID gameId, PatchGameRequest patchGameRequest)
  {
    gameRepository.updatePartialContent(gameId, patchGameRequest);
  }

  @Override
  public void delete(UUID gameId)
  {
    gameRepository.delete(gameId);
  }

  @Override
  public GameResponse findById(UUID gameId)
  {
    return GameResponse.of(gameRepository.findGameById(gameId));
  }
}
