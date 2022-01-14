package br.dev.multicode.api.http.responses;

import br.dev.multicode.entities.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {

  private String id;
  private String name;
  private String description;
  private String platform;

  public static GameResponse of(Game game) {
    return GameResponse.builder()
        .id(game.getId())
        .name(game.getName())
        .description(game.getDescription())
        .platform(game.getPlatform().name())
        .build();
  }
}
