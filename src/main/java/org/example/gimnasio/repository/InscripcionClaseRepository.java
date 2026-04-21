package org.example.gimnasio.repository;

import org.example.gimnasio.model.InscripcionClase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionClaseRepository extends JpaRepository<InscripcionClase, Integer> {

    List<InscripcionClase> findBySocioIdSocio(Integer idSocio);
}