package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Usuario;
import org.example.gimnasio.repository.UsuarioRepository;
import org.example.gimnasio.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @GetMapping("/jwt/{email}")
    public String testJwt(@PathVariable String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return jwtService.generateToken(
                usuario.getEmail(),
                usuario.getRol().name()
        );
    }
}