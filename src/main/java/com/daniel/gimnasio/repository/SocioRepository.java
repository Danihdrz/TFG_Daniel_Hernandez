package com.daniel.gimnasio.repository;

import com.daniel.gimnasio.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
    Optional<Socio> findByUsuarioEmail(String email);
}
