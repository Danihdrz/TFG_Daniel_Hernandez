package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.Asistencia;
import com.daniel.gimnasio.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @PostMapping
    public Asistencia crear(@Valid @RequestBody Asistencia asistencia) {
        return asistenciaService.guardar(asistencia);
    }

    @GetMapping
    public List<Asistencia> listar() {
        return asistenciaService.listar();
    }

    @GetMapping("/{id}")
    public Asistencia obtener(@PathVariable Integer id) {
        return asistenciaService.obtenerPorId(id);
    }

    @GetMapping("/socio/{idSocio}")
    public List<Asistencia> listarPorSocio(@PathVariable Integer idSocio) {
        return asistenciaService.listarPorSocio(idSocio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        asistenciaService.eliminar(id);
    }
}
