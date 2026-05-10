package com.triatlon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad que representa a un atleta de triatlón.
 * Extiende Persona para manejar la herencia escalable requerida por el sistema.
 */
@Entity
@Table(name = "atleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atleta extends Persona {

    /**
     * Categoría del atleta determinada por su edad.
     * Ej: Cadetes, Juvenil, Júnior, Sub-23, Absoluta, Veterano 1/2/3.
     */
    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;

    /**
     * Especialidad de triatlón según distancia.
     * Ej: SuperSprint, Sprint, Olímpica, Media, Ironman, Ultraman.
     */
    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

    /** Indica si el atleta participa en modalidad cross (BTT + trail running). */
    private Boolean modalidadCross;

    /** Ruta o nombre del archivo de foto del atleta. */
    private String foto;
}