package br.dev.multicode.services.impl;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.api.http.responses.GameResponse;
import br.dev.multicode.entities.Game;
import br.dev.multicode.repositories.GameRepository;
import br.dev.multicode.services.GameService;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GameServiceImpl implements GameService {

  @Inject
  GameRepository gameRepository;

  @Override
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
