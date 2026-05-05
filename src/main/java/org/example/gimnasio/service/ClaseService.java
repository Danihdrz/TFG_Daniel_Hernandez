package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Clase;
import org.example.gimnasio.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository claseRepository;

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
        return claseRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        claseRepository.deleteById(id);
    }
}