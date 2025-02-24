package com.playground.presentation.authentication.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
    title = "ログイン認証情報",
    requiredProperties = {"accountName", "password"})
public record LoginRequest(
    @NotBlank
        @Size(min = 1, max = 20)
        @Schema(
            title = "アカウント名",
            type = "string",
            minLength = 1,
            maxLength = 20,
            example = "AccountName")
        String accountName,
    @NotBlank
        @Size(min = 1, max = 20)
        @Schema(
            title = "パスワード",
            type = "string",
            format = "password",
            minLength = 1,
            maxLength = 20,
            example = "Password")
        String password) {}
