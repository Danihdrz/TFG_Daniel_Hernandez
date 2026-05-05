package org.example.gimnasio.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    @GetMapping
    public String listarUsuarios() {
        return "Lista de usuarios (ADMIN)";
    }

    @PostMapping
    public String crearUsuario() {
        return "Usuario creado (ADMIN)";
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        return "Usuario eliminado con id: " + id;
    }
}