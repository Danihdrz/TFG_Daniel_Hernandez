package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Entrenador;
import org.example.gimnasio.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador) {
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