package com.daniel.gimnasio.model;

public enum Duracion {

    MENSUAL(30),
    TRIMESTRAL(90),
    ANUAL(365);

    private final int dias;

    Duracion(int dias) {
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }
}
