package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Usuario;
import org.example.gimnasio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Guardar usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener todos
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    // Eliminar
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}