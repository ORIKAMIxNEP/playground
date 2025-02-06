package com.spring_boot_template.domain.module;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class IdGeneratorTest {
    private IdGenerator idGenerator;

    @BeforeEach
    void setup() {
        idGenerator = new IdGenerator();
    }

    @Test
    void testGenerateId() {
        final String id = idGenerator.generateId();
        final String ulidRegex = "^(?!.*[ILOU])[0-9A-Z]{26}$";
        assertThat(id).matches(ulidRegex);
    }
}
