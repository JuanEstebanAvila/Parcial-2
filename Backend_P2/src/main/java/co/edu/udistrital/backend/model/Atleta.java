package co.edu.udistrital.backend.model;

import com.triatlon.model.*;
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
