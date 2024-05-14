package jp.spring_boot_template;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Spring Boot Template",
            version = "1.0",
            description = "Spring Boot Template"))
public class SpringBootTemplateApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootTemplateApplication.class, args);
  }
}
