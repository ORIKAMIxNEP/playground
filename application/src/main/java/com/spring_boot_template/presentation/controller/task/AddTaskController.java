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

    @PostMapping(value = "/record", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            tags = {"record"},
            summary = "レコードを追加する",
            description = "カラム1、カラム2を受け取る" + " → レコードを追加する",
            responses = {@ApiResponse(responseCode = "204", description = "No Content")})
    public ResponseEntity<String> addRecord(@RequestBody final AddTaskRequest addTaskRequest) {
        addTaskUseCase.execute(addTaskRequest);

        return ResponseEntity.noContent().build();
    }
}
