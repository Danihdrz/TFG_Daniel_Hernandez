package com.daniel.gimnasio.controller;

import jakarta.validation.Valid;
import com.daniel.gimnasio.dto.*;
import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.*;
import com.daniel.gimnasio.repository.UsuarioRepository;
import com.daniel.gimnasio.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            Usuario user = usuarioRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Password incorrecta");
            }

            String token = jwtService.generateToken(
                    user.getEmail(),
                    user.getRol().name()
            );

            return new LoginResponseDTO(
                    token,
                    user.getEmail(),
                    user.getRol().name()
            );
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@Valid @RequestBody RegisterRequestDTO request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Usuario ya existe");
        }

        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRol(Rol.SOCIO);

        usuarioRepository.save(user);

        return new RegisterResponseDTO(
                user.getEmail(),
                "Usuario creado"
        );
    }

    @PostMapping("/register-entrenador")
    public RegisterResponseDTO registerEntrenador(@Valid @RequestBody RegisterRequestDTO request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Usuario ya existe");
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
