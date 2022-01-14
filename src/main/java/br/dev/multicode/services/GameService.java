package br.dev.multicode.services;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import br.dev.multicode.api.http.responses.GameResponse;
import java.util.UUID;

public interface GameService {

  void delete(UUID gameId);

  GameResponse findById(UUID gameId);

  void create(GameRequest postGameRequest);

  void update(UUID gameId, GameRequest putGameRequest);

  void updatePartialContent(UUID gameId, PatchGameRequest patchGameRequest);

}
