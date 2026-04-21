package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.*;
import org.example.gimnasio.repository.*;
import org.example.gimnasio.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@RequiredArgsConstructor
public class InscripcionClaseController {

    private final InscripcionClaseService service;
    private final SocioRepository socioRepository;
    private final ClaseRepository claseRepository;

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