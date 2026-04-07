package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrenador;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String especialidad;
    private String telefono;
}