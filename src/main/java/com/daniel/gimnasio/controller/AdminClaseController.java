package com.daniel.gimnasio.controller;

import com.daniel.gimnasio.model.Clase;
import com.daniel.gimnasio.service.ClaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clases")
public class AdminClaseController {

    private final ClaseService claseService;

    public AdminClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @GetMapping
    public List<Clase> listarClases() {
        return claseService.listar();
    }

    @PostMapping
    public Clase crearClase(@Valid @RequestBody Clase clase) {
        return claseService.guardar(clase);
    }

    @PutMapping("/{id}")
    public Clase actualizarClase(@PathVariable Integer id, @Valid @RequestBody Clase datos) {
        Clase clase = claseService.obtenerPorId(id);
        clase.setNombre(datos.getNombre());
        clase.setDescripcion(datos.getDescripcion());
        clase.setFechaHora(datos.getFechaHora());
        clase.setDuracion(datos.getDuracion());
        clase.setAforoMax(datos.getAforoMax());
        clase.setEntrenador(datos.getEntrenador());
        return claseService.guardar(clase);
    }

    @DeleteMapping("/{id}")
    public void eliminarClase(@PathVariable Integer id) {
        claseService.eliminar(id);
    }
}
