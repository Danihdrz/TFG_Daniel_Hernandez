package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Entrenador;
import org.example.gimnasio.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public Entrenador guardar(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public List<Entrenador> listar() {
        return entrenadorRepository.findAll();
    }

    public Entrenador obtenerPorId(Integer id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    public Entrenador obtenerPorUsuarioId(Integer idUsuario) {
        return entrenadorRepository.findByUsuarioIdUsuario(idUsuario).orElse(null);
    }

    public void eliminar(Integer id) {
        entrenadorRepository.deleteById(id);
    }
}