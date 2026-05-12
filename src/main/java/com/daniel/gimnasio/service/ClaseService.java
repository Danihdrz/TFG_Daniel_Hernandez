package com.daniel.gimnasio.service;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Clase;
import com.daniel.gimnasio.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public Clase guardar(Clase clase) {
        return claseRepository.save(clase);
    }

    public List<Clase> listar() {
        return claseRepository.findAll();
    }

    public List<Clase> listarPorEntrenador(Integer idEntrenador) {
        return claseRepository.findByEntrenadorIdEntrenador(idEntrenador);
    }

    public Clase obtenerPorId(Integer id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada"));
    }

    public void eliminar(Integer id) {
        if (!claseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Clase no encontrada");
        }
        claseRepository.deleteById(id);
    }
}
