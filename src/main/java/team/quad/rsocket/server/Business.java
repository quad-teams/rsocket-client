package team.quad.rsocket.server;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("scenario")
@NoArgsConstructor
@AllArgsConstructor
public class Business {

  @Id
  @Setter(AccessLevel.PROTECTED)
  private String id;

  @Column("sms_id")
  private String name;

  @Column("latitude")
  private Double latitude;

  @Column("longitude")
  private Double longitude;

}
