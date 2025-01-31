package com.spring_boot_template.presentation.controller.project;

import com.spring_boot_template.application.project.FetchProjectsUseCase;
import com.spring_boot_template.domain.exception.DomainNotFoundException;
import com.spring_boot_template.domain.exception.RequestInvalidException;
import com.spring_boot_template.presentation.controller.project.response.FetchProjectsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
final class FetchProjectsController {
    private final FetchProjectsUseCase fetchProjectsUseCase;

    @GetMapping(value = "/projects")
    @ResponseBody
    @Operation(
            tags = {"project"},
            summary = "プロジェクトリストを取得する",
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
                                                                FetchProjectsResponse.class))),
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
                                @Content(schema = @Schema(oneOf = {DomainNotFoundException.class})))
            })
    private ResponseEntity<?> execute() {
        final String participatingAccountIdRequest = "0000ABCDEFGHJKMNPQRSTVWXYZ";
        return ResponseEntity.ok(fetchProjectsUseCase.execute(participatingAccountIdRequest));
    }
}
