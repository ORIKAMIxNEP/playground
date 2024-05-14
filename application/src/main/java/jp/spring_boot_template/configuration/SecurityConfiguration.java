package jp.spring_boot_template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain configureHttp(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();

    return httpSecurity.build();
  }
}
