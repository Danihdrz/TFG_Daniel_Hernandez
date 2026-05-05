package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Socio;
import org.example.gimnasio.repository.SocioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socio")
@RequiredArgsConstructor
public class SocioController {

    private final SocioRepository socioRepository;

    // 🔐 PERFIL DEL USUARIO LOGUEADO
    @GetMapping("/perfil")
    public Socio verPerfil(Authentication authentication) {

        String email = authentication.getName();

        return socioRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
    }

    // 💳 PAGOS (versión simple inicial)
    @GetMapping("/pagos")
    public String verPagos(Authentication authentication) {

        String email = authentication.getName();

        return "Pagos del socio con email: " + email;
    }
}