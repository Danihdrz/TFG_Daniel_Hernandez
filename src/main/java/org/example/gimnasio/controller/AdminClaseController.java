package org.example.gimnasio.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/clases")
public class AdminClaseController {

    @GetMapping
    public String listarClases() {
        return "Lista de clases (ADMIN)";
    }

    @PostMapping
    public String crearClase() {
        return "Clase creada (ADMIN)";
    }

    @DeleteMapping("/{id}")
    public String eliminarClase(@PathVariable Integer id) {
        return "Clase eliminada con id: " + id;
    }
}