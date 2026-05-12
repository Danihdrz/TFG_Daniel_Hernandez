package com.daniel.gimnasio.service;

import com.daniel.gimnasio.model.Cuota;
import com.daniel.gimnasio.model.Duracion;
import com.daniel.gimnasio.model.Pago;
import com.daniel.gimnasio.model.Socio;
import com.daniel.gimnasio.repository.PagoRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PagoServiceTest {

    @Test
    void registrarPagoCopiesCuotaPrecioToMonto() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        when(pagoRepository.save(any(Pago.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PagoService pagoService = new PagoService(pagoRepository);
        Socio socio = new Socio();
        Cuota cuota = new Cuota();
        cuota.setPrecio(new BigDecimal("29.99"));
        cuota.setDuracion(Duracion.MENSUAL);

        Pago pago = pagoService.registrarPago(socio, cuota);

        assertThat(pago.getSocio()).isSameAs(socio);
        assertThat(pago.getCuota()).isSameAs(cuota);
        assertThat(pago.getMonto()).isEqualByComparingTo("29.99");
        assertThat(pago.getFechaPago()).isNotNull();
        assertThat(pago.getFechaExpiracion()).isEqualTo(pago.getFechaPago().plusDays(30));
    }
}
