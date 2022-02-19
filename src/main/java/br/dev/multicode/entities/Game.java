package br.dev.multicode.entities;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import br.dev.multicode.api.http.requests.GameRequest;
import br.dev.multicode.api.http.requests.PatchGameRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "games")
public class Game extends PanacheEntityBase {

  @Id
  @Column(name = "game_id", length = 37)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @EqualsAndHashCode.Include
  private String id;

  @Column(nullable = false, length = 180)
  private String name;

  @Column(nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Platform platform;

  @Column(nullable = false)
  private BigDecimal price;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  public static Game of(GameRequest postGameRequest)
  {
    return Game.builder()
        .name(postGameRequest.getName())
        .description(postGameRequest.getDescription())
        .platform(Platform.valueOf(postGameRequest.getPlatform()))
        .price(postGameRequest.getPrice())
        .build();
  }

  public void fillWith(GameRequest gameRequest)
  {
    setName(gameRequest.getName());
    setDescription(gameRequest.getDescription());
    setPlatform(Platform.valueOf(gameRequest.getPlatform()));
    setPrice(gameRequest.getPrice());
  }

  public void fillWith(PatchGameRequest patchGameRequest)
  {
    setName(defaultIfBlank(patchGameRequest.getName(), getName()));
    setDescription(defaultIfBlank(patchGameRequest.getDescription(), getDescription()));
    setPlatform(defaultIfNull(patchGameRequest.getPlatform(), getPlatform()));
    setPrice(defaultIfNull(patchGameRequest.getPrice(), getPrice()));
  }
}
