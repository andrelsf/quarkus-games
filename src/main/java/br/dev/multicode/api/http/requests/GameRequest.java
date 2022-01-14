package br.dev.multicode.api.http.requests;

import br.dev.multicode.entities.Platform;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GameRequest {

  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  private Platform platform;

}
