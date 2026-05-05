package org.example.gimnasio.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socio/clases")
public class SocioClaseController {

    @GetMapping
    public String misClases() {
        return "Clases del socio";
    }

    @PostMapping("/inscribirse/{idClase}")
    public String inscribirse(@PathVariable Integer idClase) {
        return "Inscrito en clase: " + idClase;
    }
}