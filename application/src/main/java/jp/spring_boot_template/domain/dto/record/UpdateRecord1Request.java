package jp.spring_boot_template.domain.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateRecord1Request(@NotBlank @Size(max = 10) String record1) {}
