package com.spring_boot_template.application.record;

public interface ExistsRecordUseCase {
  // レコード存在確認
  boolean execute(final long recordId);
}
