package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClase;

    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private Integer duracion; // minutos
    private Integer aforoMax;

    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    @OneToMany(mappedBy = "clase")
    private List<InscripcionClase> inscripciones;

    @OneToMany(mappedBy = "clase")
    private List<Asistencia> asistencias;
}