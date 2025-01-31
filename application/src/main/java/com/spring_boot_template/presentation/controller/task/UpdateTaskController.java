package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.task.UpdateTaskUseCase;
import com.spring_boot_template.domain.exception.DomainConflictException;
import com.spring_boot_template.domain.exception.DomainNotFoundException;
import com.spring_boot_template.domain.exception.RequestInvalidException;
import com.spring_boot_template.presentation.controller.project.request.ProjectIdRequest;
import com.spring_boot_template.presentation.controller.task.request.TaskIdRequest;
import com.spring_boot_template.presentation.controller.task.request.UpdateTaskRequest;
import com.spring_boot_template.presentation.validator.ValidUlid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class UpdateTaskController {
    private final UpdateTaskUseCase updateTaskUseCase;

    @PutMapping(
            value = "/project/{projectIdRequest}/task/{taskIdRequest}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            tags = {"task"},
            summary = "タスクを更新する",
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content =
                                @Content(
                                        schema = @Schema(oneOf = {RequestInvalidException.class}))),
                @ApiResponse(
                        responseCode = "404",
                        description = "Not Found",
                        content =
                                @Content(
                                        schema = @Schema(oneOf = {DomainNotFoundException.class}))),
                @ApiResponse(
                        responseCode = "409",
                        description = "Conflict",
                        content =
                                @Content(schema = @Schema(oneOf = {DomainConflictException.class})))
            })
    private ResponseEntity<?> execute(
            @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
            @PathVariable @ValidUlid final TaskIdRequest taskIdRequest,
            @RequestBody @Validated final UpdateTaskRequest updateTaskRequest) {
        updateTaskUseCase.execute(projectIdRequest, taskIdRequest, updateTaskRequest);
        return ResponseEntity.noContent().build();
    }
}
