package org.example.gimnasio.repository;

import org.example.gimnasio.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
}