package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.exception.ResourceNotFoundException;
import com.daniel.gimnasio.model.Pago;
import com.daniel.gimnasio.model.Socio;
import com.daniel.gimnasio.repository.PagoRepository;
import com.daniel.gimnasio.repository.SocioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socio")
public class SocioController {

    private final SocioRepository socioRepository;
    private final PagoRepository pagoRepository;

    public SocioController(SocioRepository socioRepository, PagoRepository pagoRepository) {
        this.socioRepository = socioRepository;
        this.pagoRepository = pagoRepository;
    }

    @GetMapping("/perfil")
    public Socio verPerfil(Authentication authentication) {
        return socioRepository.findByUsuarioEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado"));
    }

    @GetMapping("/pagos")
    public List<Pago> verPagos(Authentication authentication) {
        Socio socio = verPerfil(authentication);
        return pagoRepository.findBySocioIdSocio(socio.getIdSocio());
    }
}
