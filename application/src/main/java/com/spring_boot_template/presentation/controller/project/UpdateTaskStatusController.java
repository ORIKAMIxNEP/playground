package com.spring_boot_template.presentation.controller.project;

import com.spring_boot_template.application.project.UpdateTaskStatusUseCase;
import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.project.request.UpdateTaskStatusRequest;
import com.spring_boot_template.presentation.controller.project.response.UpdateTaskStatusResponse;
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
final class UpdateTaskStatusController {
    private final UpdateTaskStatusUseCase updateTaskStatusUseCase;

    @PutMapping(value = "/task/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(
            tags = {"task"},
            summary = "タスクステータスを更新する",
            description = "タスクIDを受け取る" + " → タスクステータスを更新する" + " → タスクステータスを返す",
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
                                                                UpdateTaskStatusResponse.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema =
                                                @Schema(
                                                        oneOf = {
                                                            ValidationException.class,
                                                            DomainException.class
                                                        })))
            })
    public ResponseEntity<?> execute(
            @RequestBody final UpdateTaskStatusRequest updateTaskStatusRequest) {
        return ResponseEntity.ok(updateTaskStatusUseCase.execute(updateTaskStatusRequest));
    }
}
