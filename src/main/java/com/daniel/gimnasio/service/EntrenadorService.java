package com.daniel.gimnasio.service;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Entrenador;
import com.daniel.gimnasio.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador guardar(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public List<Entrenador> listar() {
        return entrenadorRepository.findAll();
    }

    public Entrenador obtenerPorId(Integer id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrenador no encontrado"));
    }

    public Entrenador obtenerPorUsuarioId(Integer idUsuario) {
        return entrenadorRepository.findByUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Entrenador no encontrado"));
    }

    public void eliminar(Integer id) {
        if (!entrenadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entrenador no encontrado");
        }
        entrenadorRepository.deleteById(id);
    }
}
