package com.daniel.gimnasio.service;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Socio;
import com.daniel.gimnasio.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    public Socio guardar(Socio socio) {
        return socioRepository.save(socio);
    }

    public List<Socio> listar() {
        return socioRepository.findAll();
    }

    public Socio obtenerPorId(Integer id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado"));
    }

    public void eliminar(Integer id) {
        if (!socioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Socio no encontrado");
        }
        socioRepository.deleteById(id);
    }
}
