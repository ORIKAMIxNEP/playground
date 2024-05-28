package com.spring_boot_template.presentation.controller.record;

import com.spring_boot_template.application.usecase.record.impl.DeleteRecordUseCaseImpl;
import com.spring_boot_template.domain.exception.AccessDeniedException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.record.request.DeleteRecordRequest;
import com.spring_boot_template.presentation.validator.CsrfTokenValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class DeleteRecordController {
    private final CsrfTokenValidator csrfTokenValidator;
    private final DeleteRecordUseCaseImpl deleteRecordUseCaseImpl;

    @DeleteMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(
            tags = {"record"},
            summary = "レコードを削除する",
            description = "レコードIDを受け取る" + " → レコードを削除する",
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
    public ResponseEntity<?> deleteRecord(
            @RequestHeader("X-CSRF-Token") String receivedCsrfToken,
            @RequestBody final DeleteRecordRequest deleteRecordRequest) {
        csrfTokenValidator.execute(receivedCsrfToken);
        deleteRecordUseCaseImpl.execute(deleteRecordRequest);
        return ResponseEntity.noContent().build();
    }
}
