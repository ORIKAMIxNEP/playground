package com.spring_boot_template.presentation.controller.record;

import com.spring_boot_template.application.usecase.record.impl.FetchRecordUseCaseImpl;
import com.spring_boot_template.domain.exception.AccessDeniedException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.record.response.FetchRecordResponse;
import com.spring_boot_template.presentation.validator.CsrfTokenValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class FetchRecordController {
    private final CsrfTokenValidator csrfTokenValidator;
    private final FetchRecordUseCaseImpl fetchRecordUseCaseImpl;

    @GetMapping("/record/{recordId}")
    @ResponseBody
    @Operation(
            tags = {"record"},
            summary = "レコードを取得する",
            description = "レコードIDを受け取る" + " → レコードを取得する" + " → レコードID、カラム1、カラム2を返す",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                FetchRecordResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                ValidationException.class))),
                @ApiResponse(
                        responseCode = "403",
                        description = "Forbidden",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                AccessDeniedException.class)))
            })
    public ResponseEntity<?> fetchRecord(
            @RequestHeader("X-CSRF-Token") String receivedCsrfToken,
            @PathVariable final String recordId) {
        csrfTokenValidator.execute(receivedCsrfToken);
        FetchRecordResponse fetchRecordResponse = fetchRecordUseCaseImpl.execute(recordId);
        return ResponseEntity.ok(fetchRecordResponse);
    }
}
