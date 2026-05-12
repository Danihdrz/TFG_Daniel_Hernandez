package com.daniel.gimnasio.repository;

import com.daniel.gimnasio.model.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuotaRepository extends JpaRepository<Cuota, Integer> {
}
