package org.example.gimnasio.repository;

import org.example.gimnasio.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
}