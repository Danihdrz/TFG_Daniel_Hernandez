package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Clase;
import org.example.gimnasio.model.Entrenador;
import org.example.gimnasio.repository.ClaseRepository;
import org.example.gimnasio.service.EntrenadorService;
import org.example.gimnasio.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entrenador/clases")
@RequiredArgsConstructor
public class EntrenadorClaseController {

    private final ClaseRepository claseRepository;
    private final EntrenadorService entrenadorService;
    private final UsuarioService usuarioService;

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
