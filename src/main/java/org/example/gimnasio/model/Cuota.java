package org.example.gimnasio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    private Duracion duracion;

    @JsonIgnore
    @OneToMany(mappedBy = "cuota")
    private List<Pago> pagos;
}