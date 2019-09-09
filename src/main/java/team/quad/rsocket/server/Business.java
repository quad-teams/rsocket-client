package team.quad.rsocket.server;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {

  @Setter(AccessLevel.PROTECTED)
  private String id;
  private String name;
  private Double latitude;
  private Double longitude;

}
