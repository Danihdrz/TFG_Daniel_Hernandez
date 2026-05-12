package com.daniel.gimnasio.repository;

import com.daniel.gimnasio.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findBySocioIdSocio(Integer idSocio);
}
