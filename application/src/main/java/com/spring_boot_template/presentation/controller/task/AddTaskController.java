package com.spring_boot_template.presentation.controller.task;

import com.spring_boot_template.application.usecase.task.AddTaskUseCase;
import com.spring_boot_template.presentation.controller.task.request.AddTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
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
            description = "カラム1、カラム2を受け取る" + " → タスクを追加する",
            responses = {@ApiResponse(responseCode = "204", description = "No Content")})
    public ResponseEntity<String> addTask(@RequestBody final AddTaskRequest addTaskRequest) {
        addTaskUseCase.execute(addTaskRequest);

        return ResponseEntity.noContent().build();
    }
}
