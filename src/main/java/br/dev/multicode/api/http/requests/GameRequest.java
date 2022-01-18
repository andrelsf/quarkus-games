package br.dev.multicode.api.http.requests;

import br.dev.multicode.api.http.validators.constraints.ValueOfEnum;
import br.dev.multicode.entities.Platform;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @ValueOfEnum(enumClass = Platform.class)
  private String platform;

}
