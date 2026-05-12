package com.daniel.gimnasio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

class GimnasioApplicationTests {

    @Test
    void applicationClassIsSpringBootApplication() {
        assertThat(GimnasioApplication.class)
                .hasAnnotation(SpringBootApplication.class);
    }
}
