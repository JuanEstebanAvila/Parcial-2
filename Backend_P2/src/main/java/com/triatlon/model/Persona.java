package com.triatlon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**S
 * Clase base que representa a una persona en el sistema.
 * Diseñada con herencia escalable: Persona -> Atleta, Persona -> Juez (futuro).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Persona {

    /** Identificador único generado automáticamente. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre completo de la persona. */
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    /** Número de identificación (cédula, pasaporte, etc.). */
    @NotBlank(message = "La identificación no puede estar vacía")
    private String identificacion;

    /** Edad de la persona. */
    @NotNull(message = "La edad es requerida")
    @Min(value = 7, message = "La edad mínima es 7 años")
    private Integer edad;

    /** Género de la persona (M/F). */
    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    /** Correo electrónico para notificaciones de registro. */
    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo no tiene formato válido")
    private String correo;
}