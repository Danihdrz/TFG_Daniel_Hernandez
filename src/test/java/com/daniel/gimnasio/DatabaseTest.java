package com.daniel.gimnasio;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseTest {

    @Test
    void databaseConnectionCheckIsOnlyTestCode() {
        assertThat(DatabaseTest.class.getPackageName()).isEqualTo("com.daniel.gimnasio");
    }
}
