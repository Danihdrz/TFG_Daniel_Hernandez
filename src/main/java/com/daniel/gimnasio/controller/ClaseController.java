package com.daniel.gimnasio.controller;

import jakarta.validation.Valid;
import com.daniel.gimnasio.model.Clase;
import com.daniel.gimnasio.service.ClaseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping
    public Clase crear(@Valid @RequestBody Clase clase) {
        return claseService.guardar(clase);
    }

    @GetMapping
    public List<Clase> listar() {
        return claseService.listar();
    }

    @GetMapping("/{id}")
    public Clase obtener(@PathVariable Integer id) {
        return claseService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        claseService.eliminar(id);
    }

}
