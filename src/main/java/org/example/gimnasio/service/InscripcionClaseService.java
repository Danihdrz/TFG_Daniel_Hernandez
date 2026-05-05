package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.*;
import org.example.gimnasio.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InscripcionClaseService {

    private final InscripcionClaseRepository repo;

    public InscripcionClase inscribir(Socio socio, Clase clase) {

        // comprobar aforo
        int inscritos = clase.getInscripciones().size();

        if (inscritos >= clase.getAforoMax()) {
            throw new RuntimeException("Clase completa");
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
}