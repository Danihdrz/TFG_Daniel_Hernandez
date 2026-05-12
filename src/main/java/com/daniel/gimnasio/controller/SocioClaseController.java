package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Clase;
import com.daniel.gimnasio.model.InscripcionClase;
import com.daniel.gimnasio.model.Socio;
import com.daniel.gimnasio.repository.ClaseRepository;
import com.daniel.gimnasio.repository.SocioRepository;
import com.daniel.gimnasio.service.InscripcionClaseService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/socio/clases")
public class SocioClaseController {

    private final SocioRepository socioRepository;
    private final ClaseRepository claseRepository;
    private final InscripcionClaseService inscripcionClaseService;

    public SocioClaseController(SocioRepository socioRepository, ClaseRepository claseRepository, InscripcionClaseService inscripcionClaseService) {
        this.socioRepository = socioRepository;
        this.claseRepository = claseRepository;
        this.inscripcionClaseService = inscripcionClaseService;
    }

    @GetMapping
    public List<Clase> misClases(Authentication authentication) {
        Socio socio = socioRepository.findByUsuarioEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado"));

        return inscripcionClaseService.listarPorSocio(socio.getIdSocio()).stream()
                .map(InscripcionClase::getClase)
                .collect(Collectors.toList());
    }

    @PostMapping("/inscribirse/{idClase}")
    public InscripcionClase inscribirse(@PathVariable Integer idClase, Authentication authentication) {
        Socio socio = socioRepository.findByUsuarioEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado"));
        Clase clase = claseRepository.findById(idClase)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));

        return inscripcionClaseService.inscribir(socio, clase);
    }
}
