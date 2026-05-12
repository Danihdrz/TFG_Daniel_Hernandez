package com.daniel.gimnasio.dto;


public class RegisterResponseDTO {

    private String email;
    private String mensaje;
    public RegisterResponseDTO(String email, String mensaje) {
        this.email = email;
        this.mensaje = mensaje;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
