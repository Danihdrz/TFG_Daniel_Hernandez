package com.daniel.gimnasio.service;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Asistencia;
import com.daniel.gimnasio.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> listar() {
        return asistenciaRepository.findAll();
    }

    public List<Asistencia> listarPorSocio(Integer idSocio) {
        return asistenciaRepository.findBySocioIdSocio(idSocio);
    }

    public Asistencia obtenerPorId(Integer id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada"));
    }

    public void eliminar(Integer id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }
}
