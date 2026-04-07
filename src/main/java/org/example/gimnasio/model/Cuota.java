package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuotas")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuota;

    private String nombre;
    private Double precio;

    @Enumerated(EnumType.STRING)
    private Duracion duracion;

    @OneToMany(mappedBy = "cuota")
    private List<Pago> pagos;
}