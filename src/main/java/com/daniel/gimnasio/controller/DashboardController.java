package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.Usuario;
import com.daniel.gimnasio.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final UsuarioRepository usuarioRepository;

    public DashboardController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/metrics")
    public Map<String, Object> metrics() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        long totalUsuarios = usuarios.size();

        long socios = usuarios.stream()
                .filter(u -> u.getRol().name().equals("SOCIO"))
                .count();

        long entrenadores = usuarios.stream()
                .filter(u -> u.getRol().name().equals("ENTRENADOR"))
                .count();

        long admins = usuarios.stream()
                .filter(u -> u.getRol().name().equals("ADMIN"))
                .count();

        // ðŸ”¥ simulaciÃ³n SaaS (en real vendrÃ­a de pagos)
        double ingresosEstimados = socios * 29.99;

        Map<String, Object> res = new HashMap<>();

        res.put("totalUsuarios", totalUsuarios);
        res.put("socios", socios);
        res.put("entrenadores", entrenadores);
        res.put("admins", admins);
        res.put("ingresos", ingresosEstimados);

        return res;
    }
}
