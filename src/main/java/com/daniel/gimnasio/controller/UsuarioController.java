package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.dto.UsuarioResponseDTO;
import com.daniel.gimnasio.model.Usuario;
import com.daniel.gimnasio.repository.UsuarioRepository;
import com.daniel.gimnasio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public UsuarioResponseDTO crear(@Valid @RequestBody Usuario usuario) {
        return new UsuarioResponseDTO(usuarioService.guardar(usuario));
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtener(@PathVariable Integer id) {
        return new UsuarioResponseDTO(usuarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return new UsuarioResponseDTO(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }

    @GetMapping("/perfil")
    public UsuarioResponseDTO miPerfil(Authentication auth) {
        String email = auth.getName();

        return new UsuarioResponseDTO(usuarioService.obtenerPorEmail(email));
    }

    @GetMapping("/stats")
    public Map<String, Object> estadisticas() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        long total = usuarios.size();
        long socios = usuarios.stream()
                .filter(u -> u.getRol().name().equals("SOCIO"))
                .count();
        long entrenadores = usuarios.stream()
                .filter(u -> u.getRol().name().equals("ENTRENADOR"))
                .count();
        long admins = usuarios.stream()
                .filter(u -> u.getRol().name().equals("ADMIN"))
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("socios", socios);
        stats.put("entrenadores", entrenadores);
        stats.put("admins", admins);

        return stats;
    }
}
