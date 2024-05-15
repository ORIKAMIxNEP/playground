package jp.spring_boot_template.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Records {
  @Id private final long recordId;

  private final byte column1;

  private final String column2;
}
