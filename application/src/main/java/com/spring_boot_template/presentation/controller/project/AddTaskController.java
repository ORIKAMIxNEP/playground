package com.spring_boot_template.presentation.controller.project;

import com.spring_boot_template.application.project.AddTaskUseCase;
import com.spring_boot_template.domain.exception.DomainException;
import com.spring_boot_template.domain.exception.ValidationException;
import com.spring_boot_template.presentation.controller.project.request.AddTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class AddTaskController {
    private final AddTaskUseCase addTaskUseCase;

    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            tags = {"task"},
            summary = "タスクを追加する",
            description = "タスク名、ユーザーID、締め切り期日、最大延期回数を受け取る" + " → タスクを追加する",
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
    public ResponseEntity<String> execute(@RequestBody final AddTaskRequest addTaskRequest) {
        addTaskUseCase.execute(addTaskRequest);

        return ResponseEntity.noContent().build();
    }
}
