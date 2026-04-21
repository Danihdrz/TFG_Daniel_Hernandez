package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Usuario;
import org.example.gimnasio.repository.UsuarioRepository;
import org.example.gimnasio.security.JwtService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody Usuario request) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario.isPresent() &&
                usuario.get().getPassword().equals(request.getPassword())) {

            return jwtService.generateToken(usuario.get().getEmail());
        }

        return "Credenciales incorrectas";
    }
}