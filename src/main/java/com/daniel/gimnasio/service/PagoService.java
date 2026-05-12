package com.daniel.gimnasio.service;

import com.daniel.gimnasio.model.*;
import com.daniel.gimnasio.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago registrarPago(Socio socio, Cuota cuota) {

        Pago pago = new Pago();

        pago.setSocio(socio);
        pago.setCuota(cuota);
        pago.setMonto(cuota.getPrecio());
        pago.setFechaPago(LocalDate.now());
        pago.setFechaExpiracion(
                LocalDate.now().plusDays(cuota.getDuracion().getDias())
        );

        return pagoRepository.save(pago);
    }
}
