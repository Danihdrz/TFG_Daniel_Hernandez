package org.example.gimnasio.repository;

import org.example.gimnasio.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Integer> {

    List<Clase> findByNombre(String nombre);
}