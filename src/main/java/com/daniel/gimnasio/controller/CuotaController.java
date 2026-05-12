package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.Cuota;
import com.daniel.gimnasio.service.CuotaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuotas")
public class CuotaController {

    private final CuotaService cuotaService;

    public CuotaController(CuotaService cuotaService) {
        this.cuotaService = cuotaService;
    }

    @PostMapping
    public Cuota crear(@Valid @RequestBody Cuota cuota) {
        return cuotaService.guardar(cuota);
    }

    @GetMapping
    public List<Cuota> listar() {
        return cuotaService.listar();
    }

    @GetMapping("/{id}")
    public Cuota obtener(@PathVariable Integer id) {
        return cuotaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        cuotaService.eliminar(id);
    }
}
