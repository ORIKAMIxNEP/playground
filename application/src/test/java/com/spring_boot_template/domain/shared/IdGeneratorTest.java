package com.spring_boot_template.domain.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
final class IdGeneratorTest {
    @Test
    public void generateIdTest() {
        final String id = IdGenerator.generateId();

        assertThat(id.length()).isEqualTo(26);
    }
}
