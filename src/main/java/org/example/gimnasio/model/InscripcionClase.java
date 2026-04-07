package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscripciones_clases",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id_socio", "id_clase"})})
public class InscripcionClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "id_clase")
    private Clase clase;

    private LocalDateTime fechaInscripcion = LocalDateTime.now();
}