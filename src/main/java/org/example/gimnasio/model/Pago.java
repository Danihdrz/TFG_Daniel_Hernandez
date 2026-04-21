package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    private Socio socio;

    @ManyToOne
    private Cuota cuota;

    private LocalDate fechaPago;

    private LocalDate fechaExpiracion;
}