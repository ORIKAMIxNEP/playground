package jp.sandbox.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Records {
  @Id private final int recordId;

  private final String record1;

  private final String record2;
}
