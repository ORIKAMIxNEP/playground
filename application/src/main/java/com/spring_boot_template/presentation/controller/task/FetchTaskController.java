package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.usecase.task.FetchTaskByTaskIdUseCase;
import com.spring_boot_template.domain.exception.LogicValidationException;
import com.spring_boot_template.presentation.controller.task.response.FetchTaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class FetchTaskController {
    private final FetchTaskByTaskIdUseCase fetchTaskByTaskIdUseCase;

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
                                                @Schema(implementation = FetchTaskResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        implementation =
                                                                LogicValidationException.class)))
            })
    public ResponseEntity<?> fetchRecord(@PathVariable final String recordId) {
        return ResponseEntity.ok(fetchTaskByTaskIdUseCase.execute(recordId));
    }
}
