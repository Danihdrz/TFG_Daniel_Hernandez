package com.daniel.gimnasio.repository;

import com.daniel.gimnasio.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Integer> {

    List<Clase> findByNombre(String nombre);
    List<Clase> findByEntrenadorIdEntrenador(Integer idEntrenador);
}
