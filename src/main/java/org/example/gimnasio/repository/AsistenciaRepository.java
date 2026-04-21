package org.example.gimnasio.repository;

import org.example.gimnasio.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    List<Asistencia> findBySocioIdSocio(Integer idSocio);
}