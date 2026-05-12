package com.daniel.gimnasio.dto;

import com.daniel.gimnasio.model.Rol;
import com.daniel.gimnasio.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String nombre;
    private String email;
    private Rol rol;
    private LocalDateTime fechaCreacion;

    public UsuarioResponseDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.fechaCreacion = usuario.getFechaCreacion();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
