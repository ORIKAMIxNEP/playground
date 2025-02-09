package com.playground.presentation.controller.task;

import com.playground.application.task.DeleteTaskUseCase;
import com.playground.domain.exception.RequestInvalidException;
import com.playground.domain.exception.ResourceNotFoundException;
import com.playground.presentation.anotation.ValidUlid;
import com.playground.presentation.controller.project.request.ProjectIdRequest;
import com.playground.presentation.controller.task.request.TaskIdRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class DeleteTaskController {
  private final DeleteTaskUseCase deleteTaskUseCase;

  @DeleteMapping(value = "/project/{projectIdRequest}/task/{taskIdRequest}")
  @Operation(
      tags = {"task"},
      summary = "タスクを削除する",
      responses = {
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(oneOf = {RequestInvalidException.class}))),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(oneOf = {ResourceNotFoundException.class})))
      })
  private ResponseEntity<?> execute(
      @PathVariable @ValidUlid final ProjectIdRequest projectIdRequest,
      @PathVariable @ValidUlid final TaskIdRequest taskIdRequest) {
    deleteTaskUseCase.execute(projectIdRequest, taskIdRequest);
    return ResponseEntity.noContent().build();
  }
}
