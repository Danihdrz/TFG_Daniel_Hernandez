package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.dto.UsuarioResponseDTO;
import com.daniel.gimnasio.model.Usuario;
import com.daniel.gimnasio.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    private final UsuarioService usuarioService;

    public AdminUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listar().stream()
                .map(UsuarioResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UsuarioResponseDTO crearUsuario(@Valid @RequestBody Usuario usuario) {
        return new UsuarioResponseDTO(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return new UsuarioResponseDTO(usuarioService.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }
}
