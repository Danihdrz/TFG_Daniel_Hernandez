package org.example.gimnasio.controller;

import org.example.gimnasio.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JwtService jwtService;

    public TestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/jwt")
    public String testJwt() {
        return jwtService.generateToken("test@test.com");
    }
}