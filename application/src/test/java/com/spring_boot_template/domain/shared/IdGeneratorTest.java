package com.spring_boot_template.domain.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public final class IdGeneratorTest {
    @Test
    public void generateIdTest() {
        final String id = IdGenerator.generateId();

        assertThat(id.length()).isEqualTo(26);
    }
}
