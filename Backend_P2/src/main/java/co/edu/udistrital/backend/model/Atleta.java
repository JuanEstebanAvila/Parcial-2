package co.edu.udistrital.backend.model;

import co.edu.udistrital.backend.model.*;
import jakarta.persistence.Entity;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Atleta extends Personaf {

    private String categoria;

    private String especialidad;

    private Boolean modalidadCross;

    private String foto;
}

//Esta es la entidad del programa pero ¿donde esta estableciendo el nombre de la tabla de la base de datos?