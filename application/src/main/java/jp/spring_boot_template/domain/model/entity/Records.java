package jp.spring_boot_template.domain.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Records {
  private final long recordId;

  private final byte column1;

  private final String column2;
}
