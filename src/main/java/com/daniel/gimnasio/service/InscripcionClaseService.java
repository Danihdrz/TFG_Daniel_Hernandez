package com.daniel.gimnasio.service;

import com.daniel.gimnasio.model.*;
import com.daniel.gimnasio.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionClaseService {

    private final InscripcionClaseRepository repo;

    public InscripcionClaseService(InscripcionClaseRepository repo) {
        this.repo = repo;
    }

    public InscripcionClase inscribir(Socio socio, Clase clase) {

        // comprobar aforo
        int inscritos = clase.getInscripciones().size();

        if (inscritos >= clase.getAforoMax()) {
            throw new IllegalArgumentException("Clase completa");
        }

        InscripcionClase inscripcion = new InscripcionClase();
        inscripcion.setSocio(socio);
        inscripcion.setClase(clase);

        return repo.save(inscripcion);
    }

    public List<InscripcionClase> listar() {
        return repo.findAll();
    }

    public List<InscripcionClase> listarPorClase(Integer idClase) {
        return repo.findByClaseIdClase(idClase);
    }

    public List<InscripcionClase> listarPorSocio(Integer idSocio) {
        return repo.findBySocioIdSocio(idSocio);
    }
}
