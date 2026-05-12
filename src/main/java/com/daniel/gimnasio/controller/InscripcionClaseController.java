package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.*;
import com.daniel.gimnasio.repository.*;
import com.daniel.gimnasio.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionClaseController {

    private final InscripcionClaseService service;
    private final SocioRepository socioRepository;
    private final ClaseRepository claseRepository;

    public InscripcionClaseController(InscripcionClaseService service, SocioRepository socioRepository, ClaseRepository claseRepository) {
        this.service = service;
        this.socioRepository = socioRepository;
        this.claseRepository = claseRepository;
    }

    @PostMapping
    public InscripcionClase inscribir(@RequestParam Integer socioId,
                                      @RequestParam Integer claseId) {

        Socio socio = socioRepository.findById(socioId).orElseThrow();
        Clase clase = claseRepository.findById(claseId).orElseThrow();

        return service.inscribir(socio, clase);
    }

    @GetMapping
    public List<InscripcionClase> listar() {
        return service.listar();
    }
}
