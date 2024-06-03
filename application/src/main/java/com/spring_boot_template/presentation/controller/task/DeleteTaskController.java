package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.usecase.task.DeleteTaskUseCase;
import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.task.request.DeleteTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class DeleteTaskController {
    private final DeleteTaskUseCase deleteTaskUseCase;

    @DeleteMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(
            tags = {"task"},
            summary = "タスクを削除する",
            description = "タスクIDを受け取る" + " → タスクを削除する",
            responses = {
                @ApiResponse(responseCode = "204", description = "No Content"),
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
    public ResponseEntity<?> execute(@RequestBody final DeleteTaskRequest deleteTaskRequest) {
        deleteTaskUseCase.execute(deleteTaskRequest);

        return ResponseEntity.noContent().build();
    }
}
