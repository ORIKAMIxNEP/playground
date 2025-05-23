package com.playground.presentation.controller.task;

import com.playground.application.task.AddTaskUseCase;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.AddTaskRequest;
import com.playground.presentation.shared.module.BindingResultHandler;
import com.playground.presentation.shared.module.UriBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class AddTaskController {
  private final AddTaskUseCase addTaskUseCase;
  private final BindingResultHandler bindingResultHandler;
  private final UriBuilder uriBuilder;

  @PostMapping(
      value = "/project/{projectIdRequest}/task",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      tags = {"task"},
      summary = "タスクを追加する",
      responses = {
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class})))
      })
  public ResponseEntity<Void> execute(
      @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
      @RequestBody @Validated final AddTaskRequest addTaskRequest,
      final BindingResult bindingResult) {
    bindingResultHandler.handleBindingResult(bindingResult);
    final String taskId = addTaskUseCase.execute(projectIdRequest, addTaskRequest);
    final URI uri = uriBuilder.buildUri(taskId);
    return ResponseEntity.created(uri).build();
  }
}
