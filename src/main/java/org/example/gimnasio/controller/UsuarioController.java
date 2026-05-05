package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Usuario;
import org.example.gimnasio.service.UsuarioService;
import org.example.gimnasio.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    // 🟢 CREAR
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // 🟢 LISTAR (ADMIN)
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    // 🟢 POR ID
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Integer id) {
        return usuarioService.obtenerPorId(id);
    }

    // 🟢 ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }

    // 🔥 NUEVO: PERFIL DEL USUARIO LOGUEADO (JWT)
    @GetMapping("/perfil")
    public Usuario miPerfil(Authentication auth) {

        String email = auth.getName();

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
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