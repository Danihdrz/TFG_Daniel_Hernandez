package com.daniel.gimnasio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public java.util.Map<String, String> test() {
        return java.util.Map.of("status", "ADMIN");
    }
}
