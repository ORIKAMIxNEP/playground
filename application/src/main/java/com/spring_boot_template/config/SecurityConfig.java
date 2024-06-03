package com.spring_boot_template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf((csrf) -> csrf.ignoringRequestMatchers("/task"));

        return httpSecurity.build();
    }
}
