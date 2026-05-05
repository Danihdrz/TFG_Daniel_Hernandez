package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Clase;
import org.example.gimnasio.service.ClaseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clases")
@RequiredArgsConstructor
public class ClaseController {

    private final ClaseService claseService;

    @PostMapping
    public Clase crear(@RequestBody Clase clase) {
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