package org.example.gimnasio.service;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.*;
import org.example.gimnasio.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    public Pago registrarPago(Socio socio, Cuota cuota) {

        Pago pago = new Pago();

        pago.setSocio(socio);
        pago.setCuota(cuota);
        pago.setFechaPago(LocalDate.now());
        pago.setFechaExpiracion(
                LocalDate.now().plusDays(cuota.getDuracion().getDias())
        );

        return pagoRepository.save(pago);
    }
}