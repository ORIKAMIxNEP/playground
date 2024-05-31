package com.spring_boot_template.presentation.controller.record;

import com.spring_boot_template.application.usecase.record.UpdateRecordUseCase;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.record.request.UpdateRecordRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class UpdateRecordController {
    private final UpdateRecordUseCase updateRecordUseCase;

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
                                                                ValidationException.class)))
            })
    public ResponseEntity<?> updateRecord(
            @RequestBody final UpdateRecordRequest updateRecordRequest) {
        updateRecordUseCase.execute(updateRecordRequest);

        return ResponseEntity.noContent().build();
    }
}
