package com.spring_boot_template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {
    @Bean
    public SecurityFilterChain configHttpSecurity(final HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
