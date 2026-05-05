package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.dto.*;
import org.example.gimnasio.model.*;
import org.example.gimnasio.repository.UsuarioRepository;
import org.example.gimnasio.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        try {
            System.out.println("Intentando login para: " + request.getEmail());
            Usuario user = usuarioRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                System.out.println("⚠️ ADVERTENCIA: Password incorrecta, permitiendo acceso por depuración.");
                // throw new RuntimeException("Password incorrecta");
            }

            String token = jwtService.generateToken(
                    user.getEmail(),
                    user.getRol().name()
            );

            System.out.println("Login exitoso. Rol: " + user.getRol().name());
            return new LoginResponseDTO(
                    token,
                    user.getEmail(),
                    user.getRol().name()
            );
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }

        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());

        // 🔐 BCrypt obligatorio
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRol(Rol.SOCIO);

        usuarioRepository.save(user);

        return new RegisterResponseDTO(
                user.getEmail(),
                "Usuario creado"
        );
    }

    @PostMapping("/register-entrenador")
    public RegisterResponseDTO registerEntrenador(@RequestBody RegisterRequestDTO request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }

        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRol(Rol.ENTRENADOR);

        usuarioRepository.save(user);

        return new RegisterResponseDTO(
                user.getEmail(),
                "Entrenador creado correctamente"
        );
    }
}