package org.example.gimnasio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socios")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSocio;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
}