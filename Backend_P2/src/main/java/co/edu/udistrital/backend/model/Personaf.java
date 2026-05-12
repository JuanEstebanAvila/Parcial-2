package co.edu.udistrital.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public class Personaf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String identificacion;

    private Integer edad;

    private String genero;
    // Campo agregado para el envío del correo de registro (requerimiento del parcial)
    private String correo;
}
