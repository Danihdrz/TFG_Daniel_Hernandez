package com.daniel.gimnasio.controller;

import jakarta.validation.Valid;
import com.daniel.gimnasio.model.Entrenador;
import com.daniel.gimnasio.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public Entrenador crear(@Valid @RequestBody Entrenador entrenador) {
        return entrenadorService.guardar(entrenador);
    }

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.listar();
    }

    @GetMapping("/{id}")
    public Entrenador obtener(@PathVariable Integer id) {
        return entrenadorService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        entrenadorService.eliminar(id);
    }
}
