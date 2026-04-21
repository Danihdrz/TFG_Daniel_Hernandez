package org.example.gimnasio.repository;

import org.example.gimnasio.model.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuotaRepository extends JpaRepository<Cuota, Integer> {
}