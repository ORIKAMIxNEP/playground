package jp.sandbox.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNameRequest(@NotBlank @Size(max = 20) String name) {}
