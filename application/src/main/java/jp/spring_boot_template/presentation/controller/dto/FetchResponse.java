package jp.spring_boot_template.application.dto.record;

@Schema(description = "レコード")
public record FetchResponse(@Schema(description = "カラム1") short column1, @Schema(description = "カラム2") String column2) {}
