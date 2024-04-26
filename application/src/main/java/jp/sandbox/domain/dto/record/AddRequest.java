package jp.sandbox.domain.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddRequest(
    @NotBlank @Size(max = 10) String record1, @NotBlank @Size(max = 10) String record2) {}
