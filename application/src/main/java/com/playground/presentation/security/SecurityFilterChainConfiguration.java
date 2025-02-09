package com.playground.presentation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityFilterChainConfiguration {
  @Bean
  public SecurityFilterChain configureSecurityFilterChain(final HttpSecurity httpSecurity)
      throws Exception {
    httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/project/**"));
    return httpSecurity.build();
  }
}
