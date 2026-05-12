package com.daniel.gimnasio.repository;

import com.daniel.gimnasio.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {
    Optional<Entrenador> findByUsuarioIdUsuario(Integer idUsuario);
}
