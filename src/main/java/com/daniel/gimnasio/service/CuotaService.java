package com.daniel.gimnasio.service;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Cuota;
import com.daniel.gimnasio.repository.CuotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuotaService {

    private final CuotaRepository cuotaRepository;

    public CuotaService(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public Cuota guardar(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }

    public List<Cuota> listar() {
        return cuotaRepository.findAll();
    }

    public Cuota obtenerPorId(Integer id) {
        return cuotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuota no encontrada"));
    }

    public void eliminar(Integer id) {
        if (!cuotaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuota no encontrada");
        }
        cuotaRepository.deleteById(id);
    }
}
