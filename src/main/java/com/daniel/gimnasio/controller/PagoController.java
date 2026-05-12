package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.*;
import com.daniel.gimnasio.repository.*;
import com.daniel.gimnasio.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final SocioRepository socioRepository;
    private final CuotaRepository cuotaRepository;
    private final PagoRepository pagoRepository;

    public PagoController(PagoService pagoService, SocioRepository socioRepository, CuotaRepository cuotaRepository, PagoRepository pagoRepository) {
        this.pagoService = pagoService;
        this.socioRepository = socioRepository;
        this.cuotaRepository = cuotaRepository;
        this.pagoRepository = pagoRepository;
    }

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
