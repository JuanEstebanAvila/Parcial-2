package co.edu.udistrital.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Superclase del modelo que agrupa los atributos comunes de una persona
 * permitiendo así la escalabilidad del programa en un fúturo para otras entidades que hereden de persona.
 *
 * Se marca como @MappedSuperclass para que herede los campos a cada
 * subclase.
 *
 * @author Grupo Programación Avanzada
 * @version 2.1
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Persona {

    // Identificador interno autogenerado por la BD 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String identificacion;

    private Integer edad;

    private String genero;

    private String correo;
}