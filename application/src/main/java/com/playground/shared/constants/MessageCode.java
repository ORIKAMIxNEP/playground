package com.playground.shared.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageCode {
  NOT_FOUND("not-found"),
  INVALID_ULID_FORMAT("invalid-ulid-format"),
  STATUS_CANNOT_ADVANCED("status-cannot-advanced"),
  ALREADY_ASSIGNED("already-assigned"),
  DUE_DATE_CANNOT_POSTPONED("due-date-cannot-postponed");

  private final String value;
}
