package jp.spring_boot_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Template", version = "1.0", description = "Spring Boot Template"))
public class SpringBootTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootTemplateApplication.class, args);
  }
}
