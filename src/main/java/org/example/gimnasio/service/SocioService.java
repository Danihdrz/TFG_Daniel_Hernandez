package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.Socio;
import org.example.gimnasio.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public Socio guardar(Socio socio) {
        return socioRepository.save(socio);
    }

    public List<Socio> listar() {
        return socioRepository.findAll();
    }

    public Socio obtenerPorId(Integer id) {
        return socioRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        socioRepository.deleteById(id);
    }
}