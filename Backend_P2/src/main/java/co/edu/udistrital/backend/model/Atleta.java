package co.edu.udistrital.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa a un triatleta 
 * hereda los atributos básicos de Persona y agrega los atributos
 * propios de un atleta.
 *
 * @author Grupo Programación Avanzada
 * @version 2.0
 */

@Entity
@Table(name = "atleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Atleta extends Persona {

    private String categoria;

    private String especialidad;

    private Boolean modalidadCross;

     /**
     * Foto del atleta
     * Se define como LONGTEXT para soportar imágenes de cualquier tamaño.
     */
    @Column(columnDefinition = "LONGTEXT")
    private String foto;
}