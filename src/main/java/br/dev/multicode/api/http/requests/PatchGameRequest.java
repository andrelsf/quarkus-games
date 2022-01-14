package br.dev.multicode.api.http.requests;

import br.dev.multicode.entities.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchGameRequest {

  private String name;
  private String description;
  private Platform platform;

}
