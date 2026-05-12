package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.Clase;
import com.daniel.gimnasio.model.Entrenador;
import com.daniel.gimnasio.repository.ClaseRepository;
import com.daniel.gimnasio.service.EntrenadorService;
import com.daniel.gimnasio.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entrenador/clases")
public class EntrenadorClaseController {

    private final ClaseRepository claseRepository;
    private final EntrenadorService entrenadorService;
    private final UsuarioService usuarioService;

    public EntrenadorClaseController(ClaseRepository claseRepository, EntrenadorService entrenadorService, UsuarioService usuarioService) {
        this.claseRepository = claseRepository;
        this.entrenadorService = entrenadorService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Map<String, Object>> misClases(@AuthenticationPrincipal String email) {
        var usuario = usuarioService.obtenerPorEmail(email);
        if (usuario == null) return List.of();
        
        Entrenador entrenador = entrenadorService.obtenerPorUsuarioId(usuario.getIdUsuario());
        if (entrenador == null) return List.of();
        
        List<Clase> clases = claseRepository.findByEntrenadorIdEntrenador(entrenador.getIdEntrenador());
        
        return clases.stream().map(c -> Map.of(
            "idClase", (Object)c.getIdClase(),
            "nombre", (Object)c.getNombre(),
            "descripcion", (Object)c.getDescripcion(),
            "fechaHora", (Object)c.getFechaHora(),
            "duracion", (Object)c.getDuracion(),
            "aforoMax", (Object)c.getAforoMax(),
            "inscritos", (Object)c.getInscripciones().size()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}/alumnos")
    public List<Map<String, Object>> alumnosClase(@PathVariable Integer id) {
        Clase clase = claseRepository.findById(id).orElseThrow();
        return clase.getInscripciones().stream().map(i -> Map.of(
            "idSocio", (Object)i.getSocio().getIdSocio(),
            "nombre", (Object)i.getSocio().getUsuario().getNombre(),
            "email", (Object)i.getSocio().getUsuario().getEmail()
        )).collect(Collectors.toList());
    }
}

