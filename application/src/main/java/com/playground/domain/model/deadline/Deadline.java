package com.playground.domain.model.deadline;

import com.playground.domain.exception.DomainRuleViolationException;
import com.playground.domain.model.deadline.value.DueDate;
import com.playground.domain.model.deadline.value.MaxPostponeCount;
import com.playground.domain.model.deadline.value.PostponeCount;
import com.playground.shared.constants.MessageCode;
import com.playground.shared.module.MessageGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class Deadline {
  private DueDate dueDate;
  private PostponeCount postponeCount;
  private final MaxPostponeCount maxPostponeCount;

  public static Deadline createDeadline(
      final DueDate dueDate, final MaxPostponeCount maxPostponeCount) {
    final PostponeCount postponeCount = new PostponeCount(0);
    return new Deadline(dueDate, postponeCount, maxPostponeCount);
  }

  public static Deadline reconstructDeadline(
      final DueDate dueDate,
      final PostponeCount postponeCount,
      final MaxPostponeCount maxPostponeCount) {
    return new Deadline(dueDate, postponeCount, maxPostponeCount);
  }

  public void postponeDueDate(final MessageGenerator messageGenerator) {
    if (postponeCount.value() >= maxPostponeCount.value()) {
      final String message =
          messageGenerator.generateMessage(MessageCode.DUE_DATE_CANNOT_POSTPONED, null);
      throw new DomainRuleViolationException(message);
    }
    dueDate = new DueDate(dueDate.value().plusWeeks(1));
    postponeCount = new PostponeCount(postponeCount.value() + 1);
  }
}
