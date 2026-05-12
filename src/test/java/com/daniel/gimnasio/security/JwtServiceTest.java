package com.daniel.gimnasio.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService("clave_test_de_32_bytes_minimo_123456789");

    @Test
    void generatedTokenContainsEmailAndRole() {
        String token = jwtService.generateToken("socio@example.com", "SOCIO");

        assertThat(jwtService.isTokenValid(token)).isTrue();
        assertThat(jwtService.extractEmail(token)).isEqualTo("socio@example.com");
        assertThat(jwtService.extractRole(token)).isEqualTo("SOCIO");
    }
}
