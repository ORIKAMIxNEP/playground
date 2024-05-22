package com.spring_boot_template.application.record;

public interface ExistsRecordUseCase {
  boolean execute(final long recordId);
}
