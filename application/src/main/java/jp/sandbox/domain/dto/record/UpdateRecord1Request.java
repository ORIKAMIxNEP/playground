package jp.sandbox.domain.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRecord1Request(@NotBlank @Size(max = 10) String record1) {}
