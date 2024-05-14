package jp.spring_boot_template.presentation.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddRequest(short column1, @NotBlank @Size(max = 10) String column2) {}
