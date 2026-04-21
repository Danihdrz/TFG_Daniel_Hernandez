package org.example.gimnasio.repository;

import org.example.gimnasio.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findBySocioIdSocio(Integer idSocio);
}