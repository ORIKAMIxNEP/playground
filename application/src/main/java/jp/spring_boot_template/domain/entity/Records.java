package jp.spring_boot_template.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Records {
  @Id private final int recordId;

  private final short record1;

  private final String record2;
}
