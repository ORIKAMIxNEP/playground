package com.spring_boot_template.domain.record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Record {
  private final long recordId;

  private final byte column1;

  private final String column2;
}
