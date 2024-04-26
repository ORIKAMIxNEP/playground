package jp.sandbox.domain.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRecordRequest(@NotBlank @Size(max = 20) String record1) {}
