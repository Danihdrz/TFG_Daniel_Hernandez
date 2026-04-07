package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "id_cuota")
    private Cuota cuota;

    private LocalDate fechaPago;
    private Double monto;
}