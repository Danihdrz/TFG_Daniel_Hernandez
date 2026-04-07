package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsistencia;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "id_clase")
    private Clase clase;

    @Enumerated(EnumType.STRING)
    private TipoAsistencia tipo;

    private LocalDateTime fecha;
}