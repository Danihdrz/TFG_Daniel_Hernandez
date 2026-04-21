package org.example.gimnasio.controller;

import lombok.RequiredArgsConstructor;
import org.example.gimnasio.model.*;
import org.example.gimnasio.repository.*;
import org.example.gimnasio.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;
    private final SocioRepository socioRepository;
    private final CuotaRepository cuotaRepository;
    private final PagoRepository pagoRepository;

    @PostMapping
    public Pago pagar(@RequestParam Integer socioId,
                      @RequestParam Integer cuotaId) {

        Socio socio = socioRepository.findById(socioId).orElseThrow();
        Cuota cuota = cuotaRepository.findById(cuotaId).orElseThrow();

        return pagoService.registrarPago(socio, cuota);
    }

    @GetMapping
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }
}