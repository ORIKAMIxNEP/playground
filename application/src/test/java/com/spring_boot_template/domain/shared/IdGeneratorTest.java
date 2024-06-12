package com.spring_boot_template.domain.shared;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
final class IdGeneratorTest {
    @Test
    public void generateTest() {
        final String id = IdGenerator.generate();

        assertThat(id.length()).isEqualTo(26);
    }
}
