package com.playground.presentation.controller.task;

import com.playground.application.task.UpdateTaskUseCase;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceConflictException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import com.playground.presentation.controller.task.request.UpdateTaskRequest;
import com.playground.presentation.shared.module.BindingResultHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class UpdateTaskController {
  private final UpdateTaskUseCase updateTaskUseCase;
  private final BindingResultHandler bindingResultHandler;

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
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class}))),
        @ApiResponse(
            responseCode = "409",
            description = "Conflict",
            content = @Content(schema = @Schema(oneOf = {ResourceConflictException.class})))
      })
  public ResponseEntity<Void> execute(
      @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
      @PathVariable @ValidUlid final TaskIdRequest taskIdRequest,
      @RequestBody @Validated final UpdateTaskRequest updateTaskRequest,
      final BindingResult bindingResult) {
    bindingResultHandler.handleBindingResult(bindingResult);
    updateTaskUseCase.execute(projectIdRequest, taskIdRequest, updateTaskRequest);
    return ResponseEntity.noContent().build();
  }
}
