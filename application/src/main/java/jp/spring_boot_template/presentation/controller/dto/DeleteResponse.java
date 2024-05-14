package jp.spring_boot_template.application.dto.record;

@Schema(description = "ステータス")
public record DeleteResponse(@Schema(description = "成功") boolean success) {}
