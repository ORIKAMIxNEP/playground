package com.spring_boot_template.presentation.controller.record;

import com.spring_boot_template.application.usecase.record.impl.UpdateRecordUseCaseImpl;
import com.spring_boot_template.domain.exception.AccessDeniedException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.record.request.UpdateRecordRequest;
import com.spring_boot_template.presentation.validator.CsrfTokenValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class UpdateRecordController {
    private final CsrfTokenValidator csrfTokenValidator;
    private final UpdateRecordUseCaseImpl updateRecordUseCaseImpl;

    @PutMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(
            tags = {"record"},
            summary = "レコードを更新する",
            description = "レコードID、カラム1、カラム2を受け取る" + " → レコードを更新する",
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
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
    public ResponseEntity<?> updateRecord(
            @RequestHeader("X-CSRF-TOKEN") String receivedCsrfToken,
            @RequestBody final UpdateRecordRequest updateRecordRequest) {
        csrfTokenValidator.execute(receivedCsrfToken);
        updateRecordUseCaseImpl.execute(updateRecordRequest);
        return ResponseEntity.noContent().build();
    }
}
